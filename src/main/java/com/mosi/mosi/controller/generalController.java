package com.mosi.mosi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosi.mosi.bean.Comentarios;
import com.mosi.mosi.bean.Publicaciones;
import com.mosi.mosi.bean.ReaccionesPersonas;
import com.mosi.mosi.service.GeneralService;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.mosi.mosi.constantes.constante.*;

@CrossOrigin(origins = {"http://localhost:4200","*"})
@RestController
public class generalController {
    @Autowired
    private GeneralService generalService;


    /**
     * Parametros:
      {
          "IdPersona": ,
          "tipoPersona": ,
          "descripcion": ,
          "enlace": ,
          "compartida": ,
          "IdPublicacion":
      }
     *
     * */
    @PostMapping("CrearPublicacion")
    public  HashMap<String,Object> CrearPublicacion(HttpServletRequest request, HttpServletResponse response,
                                          @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idPersona = (params.containsKey(IDPERSONA) && params.get(IDPERSONA) != null && !params.get(IDPERSONA).toString().isEmpty()) ? Integer.valueOf(params.get(IDPERSONA).toString()) : null;
        Integer tipoPersona = (params.containsKey(TIPO_PERSONA) && params.get(TIPO_PERSONA) != null && !params.get(TIPO_PERSONA).toString().isEmpty()) ? Integer.valueOf(params.get(TIPO_PERSONA).toString()) : null;
        String descripcion = (params.containsKey(DESCRIPCION_PUBLICACION) && params.get(DESCRIPCION_PUBLICACION) != null && !params.get(DESCRIPCION_PUBLICACION).toString().isEmpty()) ? params.get(DESCRIPCION_PUBLICACION).toString() : null;
        String enlace = (params.containsKey(ENLACE) && params.get(ENLACE) != null && !params.get(ENLACE).toString().isEmpty()) ? params.get(ENLACE).toString() : null;
        Boolean compartida = (params.containsKey(COMPARTIDA) && params.get(COMPARTIDA) != null && !params.get(COMPARTIDA).toString().isEmpty()) ? Boolean.parseBoolean(params.get(COMPARTIDA).toString()): null;
        Integer idPub = (params.containsKey(IDPUBLICACION) && params.get(IDPUBLICACION) != null && !params.get(IDPUBLICACION).toString().isEmpty()) ? Integer.valueOf(params.get(IDPUBLICACION).toString()) : null;


        HashMap<String,Object> publicacion = generalService.crearPublicacion(idPersona,tipoPersona,descripcion,enlace,compartida,idPub);
        return publicacion;
    }
    /**
     {
     "descripcion": ,
     "IdPublicacion":,
     "compartida": ,
     }
     **/
    @PostMapping("EditarPublicacion")
    public HashMap<String,Object> EditarPublicacion(HttpServletRequest request, HttpServletResponse response,
                                           @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idPub = (params.containsKey(IDPUBLICACION) && params.get(IDPUBLICACION) != null && !params.get(IDPUBLICACION).toString().isEmpty()) ? Integer.valueOf(params.get(IDPUBLICACION).toString()) : null;
        String descripcion = (params.containsKey(DESCRIPCION_PUBLICACION) && params.get(DESCRIPCION_PUBLICACION) != null && !params.get(DESCRIPCION_PUBLICACION).toString().isEmpty()) ? params.get(DESCRIPCION_PUBLICACION).toString() : null;
        Boolean compartida = (params.containsKey(COMPARTIDA) && params.get(COMPARTIDA) != null && !params.get(COMPARTIDA).toString().isEmpty()) ? Boolean.parseBoolean(params.get(COMPARTIDA).toString()): null;

        HashMap<String,Object>  publicacion = generalService.editarPublicacion(idPub,descripcion,compartida);
        return publicacion;
    }

    @PostMapping("EliminarPublicacion")
    public String EliminarPublicacion(HttpServletRequest request, HttpServletResponse response,
                                                 @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idPub = (params.containsKey(IDPUBLICACION) && params.get(IDPUBLICACION) != null && !params.get(IDPUBLICACION).toString().isEmpty()) ? Integer.valueOf(params.get(IDPUBLICACION).toString()) : null;
        Boolean compartida = (params.containsKey(COMPARTIDA) && params.get(COMPARTIDA) != null && !params.get(COMPARTIDA).toString().isEmpty()) ? Boolean.parseBoolean(params.get(COMPARTIDA).toString()): null;

        String publicacion = generalService.eliminarPublicacion(idPub,compartida);
        return publicacion;
    }
    /**
     * Parametros:
     {
     "IdPublicacion":,
     "comentario": ,
     "compartida": ,
     "IdPersona": ,
     "tipoPersona":
     }

     *
     * */
    @PostMapping("crearComentario")
    public Comentarios comentarPublicacion(HttpServletRequest request, HttpServletResponse response,
                                           @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idPub = (params.containsKey(IDPUBLICACION) && params.get(IDPUBLICACION) != null && !params.get(IDPUBLICACION).toString().isEmpty()) ? Integer.valueOf(params.get(IDPUBLICACION).toString()) : null;
        String descripcion = (params.containsKey(COMENTARIO) && params.get(COMENTARIO) != null && !params.get(COMENTARIO).toString().isEmpty()) ? params.get(COMENTARIO).toString() : null;
        Integer idPersona = (params.containsKey(IDPERSONA) && params.get(IDPERSONA) != null && !params.get(IDPERSONA).toString().isEmpty()) ? Integer.valueOf(params.get(IDPERSONA).toString()) : null;
        Integer tipoPersona = (params.containsKey(TIPO_PERSONA) && params.get(TIPO_PERSONA) != null && !params.get(TIPO_PERSONA).toString().isEmpty()) ? Integer.valueOf(params.get(TIPO_PERSONA).toString()) : null;
        Boolean compartida = (params.containsKey(COMPARTIDA) && params.get(COMPARTIDA) != null && !params.get(COMPARTIDA).toString().isEmpty()) ? Boolean.parseBoolean(params.get(COMPARTIDA).toString()): null;

        Comentarios comentario = generalService.crearcomentario(idPub,descripcion,idPersona,tipoPersona,compartida);
        return comentario;
    }
    /**
     * Parametros:
     {
     "IdComentario":,
     "comentario":
     }

     *
     * */
    @PostMapping("EditarComentario")
    public Comentarios EditarComentario(HttpServletRequest request, HttpServletResponse response,
                                        @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idCom = (params.containsKey(IDCOMENTARIO) && params.get(IDCOMENTARIO) != null && !params.get(IDCOMENTARIO).toString().isEmpty()) ? Integer.valueOf(params.get(IDCOMENTARIO).toString()) : null;
        String descripcion = (params.containsKey(COMENTARIO) && params.get(COMENTARIO) != null && !params.get(COMENTARIO).toString().isEmpty()) ? params.get(COMENTARIO).toString() : null;

        Comentarios publicacion = generalService.editarcomentario(idCom,descripcion);
        return publicacion;
    }
    @PostMapping("eliminarComentario")
    public Comentarios eliminarComentario(HttpServletRequest request, HttpServletResponse response,
                                          @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idCom = (params.containsKey(IDCOMENTARIO) && params.get(IDCOMENTARIO) != null && !params.get(IDCOMENTARIO).toString().isEmpty()) ? Integer.valueOf(params.get(IDCOMENTARIO).toString()) : null;

        Comentarios publicacion = generalService.eliminarComentario(idCom);
        return publicacion;
    }
    /**
     * Parametros:
     {
     "IdComentario":,
     "IdPublicacion":,
     "IdPersona":,
     "tipoPersona":,
     "tipo":,
     "idReaccion":,
     "compartida":
     }

     *
     * */
    @PostMapping("reaccionarPublicacionComentario")
    public ReaccionesPersonas reaccionarPublicacionComentario(HttpServletRequest request, HttpServletResponse response,
                                          @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idCom = (params.containsKey(IDCOMENTARIO) && params.get(IDCOMENTARIO) != null && !params.get(IDCOMENTARIO).toString().isEmpty()) ? Integer.valueOf(params.get(IDCOMENTARIO).toString()) : null;
        Integer idPub = (params.containsKey(IDPUBLICACION) && params.get(IDPUBLICACION) != null && !params.get(IDPUBLICACION).toString().isEmpty()) ? Integer.valueOf(params.get(IDPUBLICACION).toString()) : null;
        Integer idPersona = (params.containsKey(IDPERSONA) && params.get(IDPERSONA) != null && !params.get(IDPERSONA).toString().isEmpty()) ? Integer.valueOf(params.get(IDPERSONA).toString()) : null;
        Integer tipoPersona = (params.containsKey(TIPO_PERSONA) && params.get(TIPO_PERSONA) != null && !params.get(TIPO_PERSONA).toString().isEmpty()) ? Integer.valueOf(params.get(TIPO_PERSONA).toString()) : null;
        Integer reaccion = (params.containsKey(IDREACCION) && params.get(IDREACCION) != null && !params.get(IDREACCION).toString().isEmpty()) ? Integer.valueOf(params.get(IDREACCION).toString()) : null;
        Integer tipo = (params.containsKey(TIPO) && params.get(TIPO) != null && !params.get(TIPO).toString().isEmpty()) ? Integer.valueOf(params.get(TIPO).toString()) : null;
        Boolean compartida = (params.containsKey(COMPARTIDA) && params.get(COMPARTIDA) != null && !params.get(COMPARTIDA).toString().isEmpty()) ? Boolean.parseBoolean(params.get(COMPARTIDA).toString()): null;

        ReaccionesPersonas publicacion = generalService.reaccionarPublicacionComentario(idCom,idPub,idPersona,tipoPersona,tipo,reaccion,compartida);
        return publicacion;
    }
    @PostMapping("editarReaccion")
    public ReaccionesPersonas editarReaccion(HttpServletRequest request, HttpServletResponse response,
                                                              @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer reaccion = (params.containsKey(IDREACCION) && params.get(IDREACCION) != null && !params.get(IDREACCION).toString().isEmpty()) ? Integer.valueOf(params.get(IDREACCION).toString()) : null;
        Integer reaccionP = (params.containsKey(ID_REACCION_PERSONA) && params.get(ID_REACCION_PERSONA) != null && !params.get(ID_REACCION_PERSONA).toString().isEmpty()) ? Integer.valueOf(params.get(ID_REACCION_PERSONA).toString()) : null;

        ReaccionesPersonas publicacion = generalService.editarReaccion(reaccionP,reaccion);
        return publicacion;
    }

    @PostMapping("EliminarReaccion")
    public ReaccionesPersonas EliminarReaccion(HttpServletRequest request, HttpServletResponse response,
                                             @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idReaccion = (params.containsKey(IDREACCION) && params.get(IDREACCION) != null && !params.get(IDREACCION).toString().isEmpty()) ? Integer.valueOf(params.get(IDREACCION).toString()) : null;

        ReaccionesPersonas publicacion = generalService.eliminarReaccion(idReaccion);
        return publicacion;
    }
}
