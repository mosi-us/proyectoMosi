package com.mosi.mosi.service;

import com.mosi.mosi.bean.Empresa;
import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.Users;
import com.mosi.mosi.repository.EmpresaRepository;
import com.mosi.mosi.repository.EstudianteRepository;
import com.mosi.mosi.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.mosi.mosi.constantes.constante.EMPRESA;
import static com.mosi.mosi.constantes.constante.ESTUDIANTE;

@Service
public class UserService {
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

    public  HashMap<String,String>  userlogin(String email, String clave){
        HashMap<String,String> objectResult = new HashMap<>();

        Boolean validar_clave = false;
        Empresa perfil = new Empresa();
        Users usuario= userRepository.findByEmail(email);
        if (usuario!= null ){
            Integer estatus = usuario.getEstatus();
            String claveUsu = usuario.getPassword();
            Integer idUsu = usuario.getId();
            Integer tipo_persona = usuario.getTipo_persona();
            if (estatus == 1/*Activo*/) {
                if (claveUsu.equals(clave)) {// clave viene encriptada desde front
                    validar_clave = true;
                    /*validar sesion en uso pendiente*/
                    if (tipo_persona == ESTUDIANTE) {/*Estudiante*/
                        Estudiante estudiante = estudianteRepository.findByUsuario(usuario);
                    }else if (tipo_persona==EMPRESA){/*agregar else para Empresa y Universidad*/
                        perfil = empresaService.consultaPerfilEmpresa(idUsu);
                    }
                    /*genero Token*/
                    String token = getJWTToken(idUsu);
                    Integer check = userRepository.updateTokenByIdUser(idUsu, token);
                    if (check > 0) {
                        objectResult.put("Mensaje", "Inicio de Sesion Exitoso");
                        //objectResult = perfil;
                    }

                } else {
                    objectResult.put("Mensaje", " Clave Invalida");
                }
            } else {
                objectResult.put("Mensaje", "Usuario Desactivado o Bloqueado");
            }
        }else {
            objectResult.put("Mensaje", " Usuario no Existe");
        }
        return objectResult;
    }

    public Object signIn(String clave, String email ){
        Object objectResult = null;
        Users nuevoUsu = new Users();
        String existEmail = userRepository.buscarEmail(email);
        if ((existEmail==null)){
                nuevoUsu.setEmail(email);
                nuevoUsu.setPassword(clave);
                nuevoUsu.setEstatus(1);
                nuevoUsu.setTipo_persona(ESTUDIANTE);
                nuevoUsu.setFecha(new Date());
                nuevoUsu = userRepository.save(nuevoUsu);
                String idUsu = nuevoUsu.getId().toString();
                objectResult= mensaje("Usuario Creado con Exito",idUsu,"idUsu");

        }else{
            objectResult = mensaje("El email ya esta registrado",null,null);
        }

        return objectResult;
    }

    private String getJWTToken(int id){
        String claveSecreta = "mySecretKey";
        List<GrantedAuthority>grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token= Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(Integer.toString(id))
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,claveSecreta.getBytes()).compact();
        return "Bearer" + token;

    }
    public static Map<String, Object> mensaje(String mensaje, String dato, String tipoDato){
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
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }
    public Users findUsersbyId(Integer id) {
        Optional<Users> users = null;
        try {
            users = userRepository.findById(id);
        } catch (Exception e) {

        }
        return users.get();
    }

}
