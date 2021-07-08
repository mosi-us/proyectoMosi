package com.mosi.mosi.service;

import com.mosi.mosi.bean.Empresa;
import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.PerfilesBloqueados;
import com.mosi.mosi.bean.Usuarios;
import com.mosi.mosi.repository.EmpresaRepository;
import com.mosi.mosi.repository.EstudianteRepository;
import com.mosi.mosi.repository.PerfilesBloqueadosRepository;
import com.mosi.mosi.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.mosi.mosi.constantes.constante.*;

@Service
public class UserService {
    @Autowired
    GeneralService generalService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    empresaService empresaService;
    @Autowired
    PerfilesBloqueadosRepository perfilesBloqueadosRepository;

    public HashMap<String, String> userlogin(String email, String clave) {
        HashMap<String, String> objectResult = new HashMap<>();

        Boolean validar_clave = false;
        Empresa perfil = new Empresa();
        Usuarios usuario = userRepository.findByEmail(email);
        if (usuario != null) {
            Integer estatus = usuario.getEstatus();
            String claveUsu = usuario.getPassword();
            Integer idUsu = usuario.getId();
            Integer tipo_persona = usuario.getTipo_persona();
            if (estatus == ACTIVO) {
                if (claveUsu.equals(clave)) {// clave viene encriptada desde front
                    validar_clave = true;
                    /*validar sesion en uso pendiente*/
                    if (tipo_persona == ESTUDIANTE) {/*Estudiante*/
                        Estudiante estudiante = estudianteRepository.consultaPerfilActivo(usuario.getId());
                    } else if (tipo_persona == EMPRESA) {/*agregar else para Empresa y Universidad*/
                        perfil = empresaService.consultaPerfilEmpresa(idUsu);
                    }
                    /*genero Token*/
                    String token = getJWTToken(idUsu);
                    usuario.setToken(token);
                    usuario.setIntentoFallido(0);
                    userRepository.save(usuario);
                    objectResult.put("Mensaje", "Inicio de Sesion Exitoso");

                } else {
                    if (tipo_persona == ESTUDIANTE) {
                        int intentosMaximos = 3;
                        Integer intentos = ((usuario.getIntentoFallido() != null) ? usuario.getIntentoFallido() : 0);
                        if (intentos < intentosMaximos) {
                            usuario.setIntentoFallido(intentos + 1);
                            userRepository.save(usuario);
                        } else {
                            usuario.setEstatus(BLOQUEADO);
                            userRepository.save(usuario);
                            /*preguntar si enviar correo notificando usuario bloqueado*/
                        }
                    }
                    if (usuario.getEstatus() == BLOQUEADO) {
                        objectResult.put("Mensaje", "Ha superado el numero de intentos permitido. Su usuario se encuentra bloqueado por favor Restablezca contraseña");
                    } else {
                        objectResult.put("Mensaje", " Clave Invalida");
                    }
                }
            } else if (estatus == BLOQUEADO) {
                objectResult.put("Mensaje", "Usuario Bloqueado, por favor Restablezca Contraseña");

            } else {
                objectResult.put("Mensaje", "Usuario Desactivado");
            }
        } else {
            objectResult.put("Mensaje", " Usuario no Existe");
        }
        return objectResult;
    }

    public Object signIn(String clave, String email) throws IOException, MessagingException {
        Object objectResult = null;
        Usuarios nuevoUsu = new Usuarios();
        String existEmail = userRepository.buscarEmail(email);
        if ((existEmail == null)) {
            nuevoUsu.setEmail(email);
            nuevoUsu.setPassword(clave);
            nuevoUsu.setEstatus(1);
            nuevoUsu.setTipo_persona(ESTUDIANTE);
            nuevoUsu.setFecha(new Date());
            nuevoUsu = userRepository.save(nuevoUsu);
            String idUsu = nuevoUsu.getId().toString();
            objectResult = mensaje("Usuario Creado con Exito", idUsu, "idUsu");

            generalService.enviarEmail(nuevoUsu.getEmail(), "Registro Exitoso a Mosi", DETALLE_REGISTRO_ESTUDIANTE);


        } else {
            objectResult = mensaje("El email ya esta registrado", null, null);
        }

        return objectResult;
    }

    private String getJWTToken(int id) {
        String claveSecreta = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(Integer.toString(id))
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, claveSecreta.getBytes()).compact();
        return "Bearer" + token;

    }

    public static Map<String, Object> mensaje(String mensaje, String dato, String tipoDato) {
        Map<String, Object> Message = new HashMap<String, Object>();

        Message.put("Mensaje", mensaje);
        Message.put("tipo de Dato", tipoDato);
        Message.put("Dato", dato);
        Message.put("tipo", "respuesta");

        return Message;
    }


    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }

    public Usuarios findUsersbyId(Integer id) {
        Optional<Usuarios> users = null;
        try {
            users = userRepository.findById(id);
        } catch (Exception e) {

        }
        return users.get();
    }

    public String updateResetClave(String email, String token, HttpServletRequest request) throws IOException, MessagingException {
        String msj = "";
        Usuarios usuarios = userRepository.findByEmail(email);
        if (usuarios.getTipo_persona() != EMPRESA) {
            if (usuarios != null && token != null) {
                usuarios.setTokenReseteo(token);
                usuarios.setFechaReseteo(new Date());
                userRepository.save(usuarios);
            }
            String resetPasswordLink = this.getSiteURL(request) + "/reset_password?token=" + token;
            String msj_est = DETALLE_EMAIL_RECUPERAR_CLAVE + email + " \n Por favor haga click en el Siguiente enlace: " + resetPasswordLink;
            estudianteService.enviarEmail(email, msj_est);
            msj = "Hemos enviado un enlace para restablecer la contraseña a su correo electrónico. Por favor, compruebe.";
        } else {
            msj = "No tiene autorizacion para cambiar contraseña";
            String mensaje = "No tiene autorización para realizar estos cambios, comuníquese con soporte al correo: xxxxxxx";
            estudianteService.enviarEmail(email, mensaje);
        }
        return msj;
    }

    public String getSiteURL(HttpServletRequest request) {
        String siteUrl = request.getRequestURL().toString();
        return siteUrl.replace(request.getServletPath(), "");

    }

    public void enviarLinkEmail(String msj, String email) throws IOException, MessagingException {
        generalService.enviarEmail(email, RECUPERAR_CLAVE_ASUNTO, msj);
    }

    public String resetClaveporToken(String token, String clave) throws IOException, MessagingException {
        String msj = "";
        Usuarios usuarios = userRepository.findByTokenReseteo(token);

        if (usuarios == null) {
            msj = "Token invalido";
        } else {
            usuarios.setPassword(clave);
            usuarios.setTokenReseteo("");
            usuarios.setIntentoFallido(0);
            usuarios.setFechaReseteo(new Date());
            userRepository.save(usuarios);
            msj = "Su Contraseña ha sido reestablecida con exito!";
            String mensaje = msj + "\n Si no cambió su contraseña, póngase en contacto con nosotros inmediatamente a traves de los numeros +56 000 0000000";
            generalService.enviarEmail(usuarios.getEmail().toString(), ASUNTO_CONFIRMACION_DE_RECUPERACION_CLAVE, mensaje);
        }

        return msj;
    }

    public String cambiarClave(Integer idUsuario, String claveActual, String claveNueva) throws IOException, MessagingException {
        String resp = "";
        Usuarios usuario = userRepository.findById(idUsuario).get();
        if (usuario.getPassword().equals(claveActual)) {
            usuario.setPassword(claveNueva);
            usuario = userRepository.save(usuario);
            resp = "Su contrasena se ha Cambiado Exitosamente";
            generalService.enviarEmail(usuario.getEmail(), ASUNTO, resp);
        } else {
            resp = "la clave que ingreso no es correcta";
            /*preguntar si se envia email*/
        }

        return resp;
    }

    public String debloquear_bloquear_Usuario(Integer idUsuario, Integer idPerfil, Integer estado) {
        PerfilesBloqueados perfilesBloqueados = new PerfilesBloqueados();
        Usuarios usuario = userRepository.findById(idUsuario).get();
        perfilesBloqueados.setUsuario(usuario);
        perfilesBloqueados.setPblIdperfil(idPerfil);
        perfilesBloqueados.setPblEstatus(estado); // 2--Bloqueado 1--Desbloqueado
        perfilesBloqueados.setPblFechacreacion(new Date());

        perfilesBloqueados = perfilesBloqueadosRepository.save(perfilesBloqueados);

        String msj = "El perfil se ha Bloqueado con Exito!!!";
        return msj;
    }

}


