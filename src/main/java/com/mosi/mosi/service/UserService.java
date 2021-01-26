package com.mosi.mosi.service;

import com.mosi.mosi.bean.Users;
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

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    EstudianteService estudianteService;

    public  HashMap<String,String>  userlogin(String nombreusuario, String clave){
        HashMap<String,String> objectResult = new HashMap<>();

        Boolean validar_clave = false;
        HashMap<String,String> perfilEstudiante = new HashMap<>();
        Users[] usuario= userRepository.findByNombre(nombreusuario);
        if ((usuario!= null )&&(usuario.length>0)) {
            Integer estatus = usuario[0].getEstatus();
            String claveUsu = usuario[0].getPassword();
            Integer idUsu = usuario[0].getId();
            Integer tipo_persona = usuario[0].getTipo_persona();
            String userName = usuario[0].getNombre();
            if (estatus == 1/*Activo*/) {
                if (claveUsu.equals(clave)) {// clave viene encriptada desde front
                    validar_clave = true;
                    /*validar sesion en uso pendiente*/
                    if (tipo_persona == 1) {/*Estudiante*/
                        List<Object[]> estudiante = estudianteRepository.consultaEstudianteCompleto(idUsu);
                        perfilEstudiante = estudianteService.doConstruirEstudiante(estudiante);
                    }/*agregar else para Empresa y Universidad*/
                    /*genero Token*/
                    String token = getJWTToken(idUsu);
                    Integer check = userRepository.updateTokenByIdUser(idUsu, token);
                    if (check > 0) {
                        perfilEstudiante.put("Mensaje", "Inicio de Sesion Exitoso");
                        objectResult = perfilEstudiante;
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

    public Object signIn(String nombreusuario, String clave, String email ){
        Object objectResult = null;
        Users nuevoUsu = new Users();
        String existEmail = userRepository.buscarEmail(email);
        Users[] nuevoNombre = userRepository.findByNombre(nombreusuario);
        if ((existEmail==null)){
            if ((nuevoNombre == null )|| (nuevoNombre.length<1)){

                nuevoUsu.setNombre(nombreusuario);
                nuevoUsu.setEmail(email);
                nuevoUsu.setPassword(clave);
                nuevoUsu.setEstatus(1);
                nuevoUsu.setFecha(new Date());
                nuevoUsu = userRepository.save(nuevoUsu);
                String idUsu = nuevoUsu.getId().toString();
                objectResult= mensaje("Usuario Creado con Exito",idUsu,"idUsu");
            }else {
                objectResult = mensaje("El Usuario ya Existe",null,null);
            }
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

}
