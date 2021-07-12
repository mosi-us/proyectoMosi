package com.mosi.mosi.service;

import com.mosi.mosi.MosiApplication;
import com.mosi.mosi.bean.*;
import com.mosi.mosi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import static com.mosi.mosi.constantes.constante.*;

@Service
public class GeneralService {
    @Autowired
    PublicacionRepository publicacionRepository;

    @Autowired
    PublicacionesPersonaRepository publicacionesPersonaRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    PublicacionesCompartidasRepository publicacionesCompartidasRepository;

    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    ReaccionesPersonasRepository reaccionesPersonasRepository;

    @Autowired
    ReaccionesRepository reaccionesRepository;



    public Object getPropertiesEmail() throws IOException {
        Properties p = new Properties();
        Properties properties = new Properties();
        InputStream file = null;
        file = MosiApplication.class.getClassLoader().getResourceAsStream("application.properties");
        p.load(file);
        properties.put(SERVER_STMP,p.getProperty(SERVER_STMP));
        properties.put(REMITENTE_EMAIL,p.getProperty(REMITENTE_EMAIL));
        properties.put(PASSWORD_EMAIL,p.getProperty(PASSWORD_EMAIL));
        properties.put(AUTH_STMP,p.getProperty(AUTH_STMP));
        properties.put(STARTTLS,p.getProperty(STARTTLS));
        properties.put(PUERTO_STMP,587);
        properties.put(SMTP_SSL, CERTIFICADO_SSL);

        return properties;
    }

    public void enviarEmail(String desti,String asunto,String cuerpo) throws MessagingException, IOException {
        Address destinatario= new InternetAddress(desti);
        Properties properties =(Properties) this.getPropertiesEmail();

        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(properties.getProperty(REMITENTE_EMAIL)));
        message.addRecipient(Message.RecipientType.TO,destinatario );   //Se podrían añadir varios de la misma manera
        message.setSubject(asunto);
        message.setText(cuerpo);
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", properties.getProperty(REMITENTE_EMAIL), properties.getProperty(PASSWORD_EMAIL));
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public  HashMap<String,Object> crearPublicacion(Integer idPersona, Integer tipoPersona, String descripcion, String enlace, Boolean compartida,Integer idPub) {
        HashMap<String,Object> resp = new HashMap<>();
        Publicaciones publicaciones = new Publicaciones();
        PublicacionesPersona publicacionesPersona = new PublicacionesPersona();

        if (compartida==Boolean.TRUE){
           return this.crearPublicacionCompartida(idPersona,idPub,tipoPersona,descripcion);
        }else {
            if (tipoPersona==ESTUDIANTE){
                Estudiante estudiante = estudianteRepository.findById(idPersona).get();
                publicaciones.setEstudiante(estudiante);
            }if (tipoPersona==EMPRESA){
                Empresa empresa = empresaRepository.findById(idPersona).get();
                publicaciones.setEmpresa(empresa);
            }
            Date ini = new Date();
            Date fin = this.obtenerFechafin(ini, 3);
            publicaciones.setPubDescripcion(descripcion);
            publicaciones.setPubEnlace(enlace);
            publicaciones.setPubEstatus(ACTIVO);
            publicaciones.setPubFechaCreacion(new Date());
            publicaciones.setPubFechaInicio(ini);
            publicaciones.setPubFechaFin(fin);
            publicaciones = publicacionRepository.save(publicaciones);

            resp.put("publicacion", publicaciones);
        return resp;

        }
    }
    public  HashMap<String,Object> crearPublicacionCompartida(Integer idPersona,Integer idPub, Integer tipoPersona, String descripcion) {
            PublicacionesCompartidas publicacionesCompartidas = new PublicacionesCompartidas();
            HashMap<String,Object> resp = new HashMap<>();
        try {
            if (tipoPersona==ESTUDIANTE){
                Estudiante estudiante = estudianteRepository.findById(idPersona).get();
                publicacionesCompartidas.setEstudiante(estudiante);
            }if (tipoPersona==EMPRESA){
                Empresa empresa = empresaRepository.findById(idPersona).get();
                publicacionesCompartidas.setEmpresa(empresa);
            }
            Date fechaInicio = new Date();
            Date fechaFin = this.obtenerFechafin(fechaInicio,3);
            Publicaciones publicacion = publicacionRepository.findById(idPub).get();
            publicacionesCompartidas.setPubId(publicacion);
            publicacionesCompartidas.setPucDescripcion(descripcion);
            publicacionesCompartidas.setPucFechaCreacion(new Date());
            publicacionesCompartidas.setPucTipoPersona(tipoPersona);
            publicacionesCompartidas.setPucFechaInicio(fechaInicio);
            publicacionesCompartidas.setPucFechaFin(fechaFin);
            publicacionesCompartidas.setPucEstatus(ACTIVO);

            publicacionesCompartidas = publicacionesCompartidasRepository.save(publicacionesCompartidas);
            resp.put("Publicacion", publicacionesCompartidas);



        } catch (Exception e) {
            e.printStackTrace();
        }

            return resp;
    }

    public Date obtenerFechafin(Date fechaInicio, Integer dias) {
        Calendar cal = Calendar.getInstance();
        Date fechaFin = new Date();
        cal.setTime(fechaInicio);
        cal.add(Calendar.DATE,dias);
        fechaFin = cal.getTime();
        return fechaFin;
    }

    public HashMap<String,Object> editarPublicacion(Integer idPub, String descripcion, Boolean compartida) {
        Publicaciones publicacion = new Publicaciones();
        HashMap<String,Object> resp = new HashMap<>();

        if (compartida==true){
            return  this.editarPublicacionCompartida(idPub,descripcion);
        }else {
            publicacion = publicacionRepository.findById(idPub).get();
            publicacion.setPubDescripcion(descripcion);
            publicacion.setPubFechaActualizacion(new Date());
            publicacion = publicacionRepository.save(publicacion);
            resp.put("Publicacion", publicacion);

            return resp;
        }
    }

    public HashMap<String, Object> editarPublicacionCompartida(Integer idPub, String descripcion) {
        HashMap<String,Object> resp = new HashMap<>();
        PublicacionesCompartidas publicacion= publicacionesCompartidasRepository.findById(idPub).get();
        publicacion.setPucDescripcion(descripcion);
        publicacion.setPucFechaActualizacion(new Date());
        publicacion = publicacionesCompartidasRepository.save(publicacion);
        resp.put("Publicacion", publicacion);

        return resp;
    }

    public String eliminarPublicacion(Integer idPub, Boolean compartida) {
        Publicaciones publicacion = new Publicaciones();
        PublicacionesCompartidas publicacionesC = new PublicacionesCompartidas();
        if (compartida==true){
            publicacionesC = publicacionesCompartidasRepository.findById(idPub).get();
            publicacionesC.setPucEstatus(ELIMINADO);
            publicacionesCompartidasRepository.save(publicacionesC);
        }else{
            publicacion = publicacionRepository.findById(idPub).get();

            publicacion.setPubEstatus(ELIMINADO);
            publicacion = publicacionRepository.save(publicacion);
        }

        return "Se ha Eliminado la publicacion con Exito!!";
    }


    public Comentarios crearcomentario(Integer idPub, String descripcion, Integer idPersona, Integer tipoPersona,Boolean compartido) {
        Comentarios comentario = new Comentarios();
        if (compartido==true){
            PublicacionesCompartidas publicacion = publicacionesCompartidasRepository.findById(idPub).get();
            comentario.setPublicacionesCompartidas(publicacion);
        }else{
            Publicaciones publicacion = publicacionRepository.findById(idPub).get();
            comentario.setPubId(publicacion);
        }
        if (tipoPersona==ESTUDIANTE){
            Estudiante estudiante = estudianteRepository.findById(idPersona).get();
            comentario.setEstudiante(estudiante);
        }
        if (tipoPersona==EMPRESA){
            Empresa empresa = empresaRepository.findById(idPersona).get();
            comentario.setEmpresa(empresa);
        }
        comentario.setDescripcionComentario(descripcion);
        comentario.setComTipoPersona(tipoPersona);
        comentario.setComEstatus(ACTIVO);
        comentario.setComFechaCreacion(new Date());
        comentario = comentarioRepository.save(comentario);


        return comentario;
    }

    public Comentarios editarcomentario(Integer idCom, String descripcion) {
         Comentarios comentario = comentarioRepository.findById(idCom).get();

         comentario.setDescripcionComentario(descripcion);
         comentario.setComFechaActualizacion(new Date());
         comentario = comentarioRepository.save(comentario);

        return comentario;
    }

    public Comentarios eliminarComentario(Integer idCom) {
        Comentarios comentario = comentarioRepository.findById(idCom).get();

        comentario.setComEstatus(ELIMINADO);
        comentario = comentarioRepository.save(comentario);
        return comentario;
    }

    public ReaccionesPersonas reaccionarPublicacionComentario(Integer idCom, Integer idPub, Integer idPersona, Integer tipoPersona, Integer tipo, Integer reaccion, Boolean compartida) {
        ReaccionesPersonas reacciones = new ReaccionesPersonas();
        if (tipoPersona==ESTUDIANTE){
            Estudiante persona = estudianteRepository.findById(idPersona).get();
            reacciones.setEstudiante(persona);
        }
        if (tipoPersona==EMPRESA){
            Empresa persona = empresaRepository.findById(idPersona).get();
            reacciones.setEmpresa(persona);
        }
        if (tipo==TIPO_PUBLICACION){
            if (compartida==true){
                PublicacionesCompartidas publicaciones = publicacionesCompartidasRepository.findById(idPub).get();
                reacciones.setPublicacionesCompartidas(publicaciones);
            }else {
                Publicaciones publicaciones = publicacionRepository.findById(idPub).get();
                reacciones.setPublicacion(publicaciones);
            }
        }
        if (tipo==TIPO_COMENTARIO){
                Comentarios comentarios = comentarioRepository.findById(idCom).get();
                reacciones.setComentarios(comentarios);
        }
        reacciones.setReacciones(reaccionesRepository.findById(reaccion).get());
        reacciones.setRepFechaCreacion(new Date());
        reacciones.setRepEstatus(ACTIVO);
        reacciones = reaccionesPersonasRepository.save(reacciones);


        return reacciones;
    }

    public ReaccionesPersonas editarReaccion(Integer reaccionP, Integer reaccion) {
        ReaccionesPersonas reacciones = reaccionesPersonasRepository.findById(reaccionP).get();

        reacciones.setReacciones(reaccionesRepository.findById(reaccion).get());
        reacciones = reaccionesPersonasRepository.save(reacciones);

        return reacciones;
    }

    public ReaccionesPersonas eliminarReaccion(Integer idReaccion) {
        ReaccionesPersonas reacciones = reaccionesPersonasRepository.findById(idReaccion).get();

        reacciones.setRepEstatus(ELIMINADO);
        reacciones = reaccionesPersonasRepository.save(reacciones);

        return reacciones;
    }
}
