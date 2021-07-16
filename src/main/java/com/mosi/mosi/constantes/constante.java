package com.mosi.mosi.constantes;

public interface constante {

    String APELLIDOS = "Apellidos";
    String CARRERA_ID = "carID";
    String CLAVE = "clave";
    String IDPERSONA ="IdPersona";
    String IMAGEN64="imagen";
    String VIDEOBASE64="video";
    String CODIGO_PAIS = "codigo_pais";
    String CORREO = "correo";
    String DEPORTE_ID = "depId";
    String DESCRIPCION_EST = "descripcion_estudiante";
    String EMAIL = "email";
    String TOKEN = "token";
    String FECHA = "fecha";
    String IDIOMAS ="Idiomas";
    String ID_USER = "idUser";
    String NOMBRES = "Nombres";
    String PAIS_ID = "paisId";
    String PAISES = "paises";
    String CIUDAD = "ciudadId";
    String TELEFONO = "telefono";
    String IDIOMA_ID = "idiId";
    String UNIVERSIDAD_ID = "uniId";
    String ESPECIALIDAD_ID = "espId";
    String SEMESTRE = "semestre";
    String PASATIEMPO = "idPasatiempo";
    String SOFTWARE_TECNOLOGIA = "sytId";
    String HABILIDADES = "hamId";
    String PASION = "Descripcion";
    String INVESTIGACIONES= "idInvestigaciones";
    String USERNAME = "userName";
    String ASIGNATURA = "asignatura";
    String TIPO = "tipo";
    String TIPO_PERSONA = "tipoPersona";
    String NOMBRE_PERFIL = "nombre";
    String SIZE = "size";
    String ID_ASIGNATURA = "idAsignatura";
    String ID_ESTUDIANTE = "idEstudiante";
    String ID_EMPRESA = "idEmpresa";
    String ID = "id";
    String LUGAR = "idLugar";
    String RESPUESTAS="respuestas";
    String CARACTERISTICAS = "Caracteristicas";
    String POSTULACION = "idPostulacion";
    String PERFILES = "carreras";

    String TITULO_ASI ="titulo";
    String DESCRIPCION_ASI ="descripcion_asignatura";
    String PREGUNTAS ="preguntas";
    String TIPO_ASI="tipo";

    String NOMBRE_PASATIEMPO = "nombrePasatiempo";
    String NOMBRE_SYT = "nombreSYT";
    /*EMPRESA*/
    String DESCRIPCION_EMP = "descripcion_empresa";
    String RUBRO = "rubro";
    String UBICACION_EMP = "ubicacion";
    String SITIO_WEB_EMP = "sitioWeb";
    String NOMBRE_EMP = "nombreEmpresa";
    String RAZON_SOCIAL_EMP = "razonSocial";
    String MISION_EMP = "mision";
    String VISION = "vision";
    /**Lugar*/
    Integer PRESENCIAL = 1;
    Integer REMOTO = 2;
    Integer AMBOS =3;
    /**Niveles*/
    Integer BASICO = 1;
    Integer INTERMEDIO = 2;
    Integer AVANZADO =3;

    /**Asignaturas**/
    Integer PRACTICAS = 1;
    Integer DESAFIOS = 2;
    /**estatus Notificaciones*/
    Integer ENVIADO = 1;
    Integer RECIBIDO = 2;
    /** Estatus de Postulaciones*/
    Integer EN_ESPERA = 1;
    Integer ACEPTADO = 2;
    Integer RECHAZADO = 3;
    Integer ELIMINADO = 4;

    /**Estatus Usuario*/
    Integer INACTIVO = 0;
    Integer ACTIVO = 1;
    Integer BLOQUEADO = 2;
    /**Tipos de Persona*/
    Integer ESTUDIANTE = 1;
    Integer EMPRESA = 2;
    Integer UNIVERSIDAD = 3;
    Integer ADMINISTRADOR_MOSI = 4;

    String TITULO_NOTIFICACION_POSTULACION = " se ha postulado a ";
    String TITULO_NOTIFICACION_SELECCION = " te ha SELECCIONADO a ";


    String SERVER_STMP ="mail.smtp.host";
    String REMITENTE_EMAIL = "mail.smtp.user";
    String PASSWORD_EMAIL = "mail.smtp.clave";
    String AUTH_STMP="mail.smtp.auth";
    String STARTTLS ="mail.smtp.starttls.enable";
    String PUERTO_STMP = "mail.smtp.port";
    String CERTIFICADO_SSL = "smtp.gmail.com";
    String SMTP_SSL ="mail.smtp.ssl.trust";
    String GOOGLE ="google.clientId";
    String FACEBOOK = "facebook.clientId";

    String ASUNTO = "Tienes una Notificacion de MOSI";
    String RECUPERAR_CLAVE_ASUNTO = "Recupera tu Acceso a MOSI";
    String ASUNTO_CONFIRMACION_DE_RECUPERACION_CLAVE = "Acabas de Restablecer tu contraseña";
    String DETALLE_EMAIL_RECHAZA_ESTUDIANTE = "Se le informa que No ha sido seleccionado por la empresa ";
    String DETALLE_EMAIL_SELECCIONAR_ESTUDIANTE = "Felicitaciones, has sido seleccionado por la empresa ";
    String DETALLE_EMAIL_SELECCIONAR_EMPRESA= "Has seleccionado al estudiante ";
    String DETALLE_EMAIL_RECUPERAR_CLAVE = "Estimado(a)\n" +
            "Usted desea restaurar la contraseña de su cuenta asociada a :\n";
    String DETALLE_REGISTRO_ESTUDIANTE = "BIENVENIDO, Te has registrado exitosamente en MOSI";
    String DETALLE_MENSJE_NO_POSTULA_ESTUDIANTE = "Se ha postulado Exitosamente, se recomienda volver a postular respondiendo las preguntas para que su postulacion tenga mas relevancia";


    String CLAVE_ACTUAL = "claveActual";
    String PERFIL = "perfil";
    String ESTATUS = "estatus" ;
    String DESCRIPCION_PUBLICACION = "descripcion" ;
    String IDPUBLICACION = "IdPublicacion";
    String ENLACE = "enlace";
    String COMENTARIO = "comentario";
    String IDCOMENTARIO = "idComentario";
    String COMPARTIDA = "compartida";
    String IDREACCION = "idReaccion";
    String ID_REACCION_PERSONA = "idReaccionPersona";

    Integer TIPO_PUBLICACION = 1;
    Integer TIPO_COMENTARIO = 2;
    String ID_SEGUIDOR = "idSeguidor";
    String ID_SEGUIDO = "idSeguido";
    String ID_SEGUIDORES= "idSeg";


    String TIPO_PERSONA_SEGUIDOR = "tipoPersonaSeguidor";
    String TIPO_PERSONA_SEGUIDO = "tipoPersonaSeguido";

}
