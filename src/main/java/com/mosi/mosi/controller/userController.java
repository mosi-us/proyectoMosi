package com.mosi.mosi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosi.mosi.bean.Imagen;
import com.mosi.mosi.constantes.CustomLoggerLevelEnum;
import com.mosi.mosi.repository.*;
import com.mosi.mosi.service.*;
import net.bytebuddy.utility.RandomString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.mosi.mosi.constantes.constante.*;

@CrossOrigin(origins = {"http://localhost:4200","*"})
@RestController
public class userController {
    @Autowired
    ImagenRepository imagenRepository;
    @Autowired
    GeneralService generalService;
    @Autowired
    universidadService universidadService;
    @Autowired
    ciudadService ciudadService;
    @Autowired
    carreraService carreraService;
    @Autowired
    UserService userService;
    @Autowired
    paisService paisService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    DeporteMaestroRepository deporteMaestroRepository;

    @Autowired
    IdiomaMaestroRepository idiomaMaestroRepository;
    @Autowired
    CarreraMaestroRepository carreraMaestroRepository;
    @Autowired
    DeporteRepository deporteRepository;
    @Autowired
    UniversidadRepository universidadRepository;
    @Autowired
    IdiomaRepository idiomaRepository;
    @Autowired
    CarreraRepository carreraRepository;
    @Autowired
    PasatiempoRepository pasatiempoRepository;
    @Autowired
    CiudadesRepository ciudadesRepository;
    @Autowired
    SoftwareTecnologiaRepository softwareTecnologiaRepository;
    @Autowired
    HabilidadesBlandasRepository habilidadesBlandasRepository;

    @Autowired
    HabilidadesBlandasMaestroRepository habilidadesBlandasMaestroRepository;
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    SoftwareTecnologiaMaestroRepository softwareTecnologiaMaestroRepository;
    @Autowired
    PasatiempoMaestroRepository pasatiempoMaestroRepository;
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    PaisesRepository paisesRepository;
    @Autowired
    private DetalleEstudianteRepository detalleEstudianteRepository;

    static Logger log = LogManager.getLogger(userController.class);


    /** Parametros
     *{"clave":"","email":""}
     * */
    @PostMapping("signInEstudiante")
    public Object signInEstudiante(HttpServletRequest request, HttpServletResponse response,
                                           @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String clave 	= (params.containsKey(CLAVE) && params.get(CLAVE) != null && !params.get(CLAVE).toString().isEmpty()) ?params.get(CLAVE).toString() : null;
        String email 	= (params.containsKey(EMAIL) && params.get(EMAIL) != null && !params.get(EMAIL).toString().isEmpty()) ?params.get(EMAIL).toString() : null;

        Object objectResult = null;
            try{
                objectResult = userService.signIn(clave, email);


            }catch(HttpClientErrorException e) {
                log.error("Se produjo un error al procesar la solicitud. (" + e.getMessage() + ")");
                log.log(CustomLoggerLevelEnum.EXCEPTION.LEVEL(), "Se produjo un error al procesar la solicitud", e);
                objectResult = estudianteService.commonErrorMessage(response);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return objectResult;
    }


    /** Parametros
     {"email":"","clave":""}
     * */
    @PostMapping("userLogin")
    public HashMap<String,String> loginUser(HttpServletRequest request, HttpServletResponse response,
                                            @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        HashMap<String,String> objectResult = new HashMap<>();
        String email 	= (params.containsKey(EMAIL) && params.get(EMAIL) != null) ? params.get(EMAIL).toString() : null;
        String clave = (params.containsKey(CLAVE) && params.get(CLAVE) != null) ? params.get(CLAVE).toString() : null;

        if ((!email.isEmpty()) && (!clave.isEmpty())){
            try{
                 objectResult = userService.userlogin(email,clave);



            }catch(HttpClientErrorException e) {
                log.error("Se produjo un error al procesar la solicitud. (" + e.getMessage() + ")");
                log.log(CustomLoggerLevelEnum.EXCEPTION.LEVEL(), "Se produjo un error al procesar la solicitud", e);
                objectResult.put("Mensaje", "Verifique los datos enviados");
                objectResult.put("tipo", "error");
            }
        }else{
            objectResult.put("Mensaje", "Verifique los datos enviados");
            objectResult.put("tipo", "error");
        }
        return objectResult;
    }
    @PostMapping("olvidarContrasena")
    public String olvidarContrasena(HttpServletRequest request, HttpServletResponse response,
                                            @ApiBodyObject(clazz = String.class) @RequestBody String json) throws IOException, MessagingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);

        String email 	= (params.containsKey(EMAIL) && params.get(EMAIL) != null) ? params.get(EMAIL).toString() : null;
        String token = RandomString.make(30);


        String msj = userService.updateResetClave(email,token,request);



        return msj;
    }
    @PostMapping("reset_password")
    public String reset_password(HttpServletRequest request, HttpServletResponse response,
                                    @ApiBodyObject(clazz = String.class) @RequestBody String json) throws IOException, MessagingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String token 	= (params.containsKey(TOKEN) && params.get(TOKEN) != null) ? params.get(TOKEN).toString() : null;
        String clave 	= (params.containsKey(CLAVE) && params.get(CLAVE) != null) ? params.get(CLAVE).toString() : null;
        String msj = userService.resetClaveporToken(token,clave);

        return msj;
    }
    /**
     * Parametros:
     * Imagen: la imagen codificada en base64
     * idUser: id del usuario que esta subiendo la imagen
     * size: tamaño de la imagen expresada en MB
     * el formato debe ser png
     {"imagen":"",
     "idUser":1,
     "size": 2}
     * */
    @PostMapping("subirImagen")
    public String subirImagen(HttpServletRequest request, HttpServletResponse response,
                                 @ApiBodyObject(clazz = String.class) @RequestBody String json) throws IOException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String imagen 	= (params.containsKey(IMAGEN64) && params.get(IMAGEN64) != null) ? params.get(IMAGEN64).toString() : null;
        Integer idUsuario = (params.containsKey(ID_USER) && params.get(ID_USER) != null) ? Integer.valueOf(params.get(ID_USER).toString()) : null;
        Integer tamaño = (params.containsKey(SIZE) && params.get(SIZE) != null) ? Integer.valueOf(params.get(SIZE).toString()): null;
        Integer sizeMax =6;
        String path = "C:\\Users\\mbarrera\\Documents\\MOSI\\mosi\\src\\main\\resources\\file";

        String msj="";
        if (tamaño<sizeMax){
            String pathCompleto =path + "\\imagen_" + idUsuario  + ".png";
            byte[] fileBytes = Base64.getDecoder().decode(imagen);
            OutputStream out = new FileOutputStream(pathCompleto);
            out.write(fileBytes);
            out.close();

            Imagen img = new Imagen();
            img.setImgRuta(pathCompleto);
            img.setUsuIdCreacion(idUsuario);
            img.setImgFechaCreacion(new Date());
            img.setImgEstatus(ACTIVO);
            img = imagenRepository.save(img);
            msj = "Se ha subido Exitosamente la imagen";
       }


        return msj;
    }


    @PostMapping("getImagen")
    public HashMap<String,Object> getImagen(HttpServletRequest request, HttpServletResponse response,
                              @ApiBodyObject(clazz = String.class) @RequestBody String json) throws IOException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idUsuario = (params.containsKey(ID_USER) && params.get(ID_USER) != null) ? Integer.valueOf(params.get(ID_USER).toString()) : null;

        Imagen imagen = imagenRepository.findByUsuIdCreacion(idUsuario);

        HashMap<String,Object> src= new HashMap<>();
        src.put("src: ", imagen.getImgRuta());
        return src;
    }

    @PostMapping("subirVideo")
    public String subirVideo(HttpServletRequest request, HttpServletResponse response,
                              @ApiBodyObject(clazz = String.class) @RequestBody String json) throws IOException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);

        Integer idUsuario = (params.containsKey(ID_USER) && params.get(ID_USER) != null) ? Integer.valueOf(params.get(ID_USER).toString()) : null;
        String video 	= (params.containsKey(VIDEOBASE64) && params.get(VIDEOBASE64) != null) ? params.get(VIDEOBASE64).toString() : null;

        String path = "C:\\Users\\mbarrera\\Documents\\MOSI\\mosi\\src\\main\\resources\\file";

        String msj="";

            String pathCompleto =path + "\\video" + idUsuario  + ".mp4";
            byte[] fileBytes = Base64.getDecoder().decode(video);
            OutputStream out = new FileOutputStream(pathCompleto);
            out.write(fileBytes);
            out.close();


        return null;
    }

}

