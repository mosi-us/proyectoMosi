package com.mosi.mosi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosi.mosi.constantes.CustomLoggerLevelEnum;
import com.mosi.mosi.repository.*;
import com.mosi.mosi.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.mosi.mosi.constantes.constante.CLAVE;
import static com.mosi.mosi.constantes.constante.EMAIL;


@RestController
public class userController {
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



    /*
    * Metodo para guardar el perfil del Estudiante
    *
    * */

    /*
    * Metodo Para Obtener Perfil del Estudiante
    * */


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

}

