package com.mosi.mosi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosi.mosi.bean.PublicacionesEntity;
import com.mosi.mosi.constantes.CustomLoggerLevelEnum;
import com.mosi.mosi.service.GeneralService;
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
import java.io.IOException;
import java.util.Map;

import static com.mosi.mosi.constantes.constante.*;

@CrossOrigin(origins = {"http://localhost:4200","*"})
@RestController
public class generalController {
    @Autowired
    private GeneralService generalService;

    @PostMapping("CrearPublicacion")
    public PublicacionesEntity CrearPublicacion(HttpServletRequest request, HttpServletResponse response,
                                                @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idPersona = (params.containsKey(IDPERSONA) && params.get(IDPERSONA) != null && !params.get(IDPERSONA).toString().isEmpty()) ? Integer.valueOf(params.get(IDPERSONA).toString()) : null;
        Integer tipoPersona = (params.containsKey(TIPO_PERSONA) && params.get(TIPO_PERSONA) != null && !params.get(TIPO_PERSONA).toString().isEmpty()) ? Integer.valueOf(params.get(TIPO_PERSONA).toString()) : null;
        String descripcion = (params.containsKey(DESCRIPCION_PUBLICACION) && params.get(DESCRIPCION_PUBLICACION) != null && !params.get(DESCRIPCION_PUBLICACION).toString().isEmpty()) ? params.get(DESCRIPCION_PUBLICACION).toString() : null;
        String enlace = (params.containsKey(ENLACE) && params.get(ENLACE) != null && !params.get(ENLACE).toString().isEmpty()) ? params.get(ENLACE).toString() : null;


        PublicacionesEntity publicacion = generalService.crearPublicacion(idPersona,tipoPersona,descripcion,enlace);
        return publicacion;
    }
    @PostMapping("EditarPublicacion")
    public PublicacionesEntity EditarPublicacion(HttpServletRequest request, HttpServletResponse response,
                                                @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idPub = (params.containsKey(IDPUBLICACION) && params.get(IDPUBLICACION) != null && !params.get(IDPUBLICACION).toString().isEmpty()) ? Integer.valueOf(params.get(IDPUBLICACION).toString()) : null;
        String descripcion = (params.containsKey(DESCRIPCION_PUBLICACION) && params.get(DESCRIPCION_PUBLICACION) != null && !params.get(DESCRIPCION_PUBLICACION).toString().isEmpty()) ? params.get(DESCRIPCION_PUBLICACION).toString() : null;

        PublicacionesEntity publicacion = generalService.editarPublicacion(idPub,descripcion);
        return publicacion;
    }

    @PostMapping("EliminarPublicacion")
    public String EliminarPublicacion(HttpServletRequest request, HttpServletResponse response,
                                                 @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idPub = (params.containsKey(IDPUBLICACION) && params.get(IDPUBLICACION) != null && !params.get(IDPUBLICACION).toString().isEmpty()) ? Integer.valueOf(params.get(IDPUBLICACION).toString()) : null;

        String publicacion = generalService.eliminarPublicacion(idPub);
        return publicacion;
    }

}
