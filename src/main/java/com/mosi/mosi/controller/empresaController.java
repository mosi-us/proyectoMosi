package com.mosi.mosi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosi.mosi.bean.*;
import com.mosi.mosi.repository.AsignaturaRepository;
import com.mosi.mosi.repository.EstudianteRepository;
import com.mosi.mosi.repository.PostulacionesRepository;
import com.mosi.mosi.repository.PreguntasRepository;
import com.mosi.mosi.service.EstudianteService;
import com.mosi.mosi.service.GeneralService;
import com.mosi.mosi.service.UserService;
import com.mosi.mosi.service.empresaService;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static com.mosi.mosi.constantes.constante.*;

@RestController
public class empresaController {
    @Autowired
    GeneralService generalService;
    @Autowired
    PostulacionesRepository postulacionesRepository;
    @Autowired
    empresaService empresaService;
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    PreguntasRepository preguntasRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    private EstudianteService estudianteService;

    /**PARAMETROS:
     {"descripcion_empresa": " ",
     "rubro":4 ,
     "ubicacion": ,
     "paisId":4 ,
     "sitioWeb":"",
     "codigo_pais": "",
     "correo": "epacompania@gmail.com",
     "nombreEmpresa":"EPA,C.A" ,
     "razonSocial":"",
     "telefono":"",
     "mision":"",
     "vision":"",
     "idUser":22}
     * */
    @PostMapping("/guardarPerfilEmpresa")
    public Empresa guardarPerfilEmpresa(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);

        Empresa emp = new Empresa();

        String descripcion 	= (params.containsKey(DESCRIPCION_EMP) && params.get(DESCRIPCION_EMP) != null) ? params.get(DESCRIPCION_EMP).toString() : null;
        Integer rubro 	= (params.containsKey(RUBRO) && params.get(RUBRO) != null) ? Integer.valueOf(params.get(RUBRO).toString()) : null;
        String ubicacion 	= (params.containsKey(UBICACION_EMP) && params.get(UBICACION_EMP) != null && !params.get(UBICACION_EMP).toString().isEmpty())
                ? params.get(UBICACION_EMP).toString() : null;
        Integer pais 	= (params.containsKey(PAIS_ID) && params.get(PAIS_ID) != null && !params.get(PAIS_ID).toString().isEmpty())
                ? Integer.valueOf(params.get(PAIS_ID).toString()) : null;
        String sitioW 	= (params.containsKey(SITIO_WEB_EMP) && params.get(SITIO_WEB_EMP) != null && !params.get(SITIO_WEB_EMP).toString().isEmpty())
                ? params.get(SITIO_WEB_EMP).toString() : null;
        String codigoPais 	= (params.containsKey(CODIGO_PAIS) && params.get(CODIGO_PAIS) != null && !params.get(CODIGO_PAIS).toString().isEmpty())
                ? params.get(CODIGO_PAIS).toString() : null;
        String correo  = (params.containsKey(CORREO) && params.get(CORREO) != null && !params.get(CORREO).toString().isEmpty()) ? params.get(CORREO).toString() : null;
        String nombre 	= (params.containsKey(NOMBRE_EMP) && params.get(NOMBRE_EMP) != null && !params.get(NOMBRE_EMP).toString().isEmpty())
                ? params.get(NOMBRE_EMP).toString() : null;
        String razonSocial = (params.containsKey(RAZON_SOCIAL_EMP) && params.get(RAZON_SOCIAL_EMP) != null && !params.get(RAZON_SOCIAL_EMP).toString().isEmpty())
                ? params.get(RAZON_SOCIAL_EMP).toString() : null;
        String telefono = (params.containsKey(TELEFONO) &&  params.get(TELEFONO) != null) ? params.get(TELEFONO).toString() : null;
       String mision = (params.containsKey(MISION_EMP) &&  params.get(MISION_EMP) != null) ? params.get(MISION_EMP).toString() : null;
        String vision = (params.containsKey(VISION) && params.get(VISION) != null && !params.get(VISION).toString().isEmpty())
                ? params.get(VISION).toString() : null;
        Integer usuId= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty() ? Integer.valueOf(params.get(ID_USER).toString()) : null );
        if ( rubro!= null && pais!=null  && correo!=null && nombre!=null){
            emp = empresaService.guardarPerfilEmpresa(descripcion,rubro,ubicacion,pais,sitioW,codigoPais,correo,nombre,razonSocial,telefono,mision,vision,usuId);
        }


        return emp;
    }
    @PostMapping("/consultarPerfilEmpresa")
    public  Empresa consultarPerfilEmpresa(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Empresa perfil = new Empresa();
        Integer usuId= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty() ? Integer.valueOf(params.get(ID_USER).toString()) : null );
        perfil = empresaService.consultaPerfilEmpresa(usuId);
        return perfil;

    }
   /* @PostMapping("/crearCaracteristicasEstudiante")
    public  HashMap<String,String> crearCaracteristicasEstudiante(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        HashMap<String,String> perfil = new HashMap<>();
        Integer usuId= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty() ? Integer.valueOf(params.get(ID_USER).toString()) : null );
        perfil = empresaService.consultaPerfilEmpresa(usuId);
        return perfil;

    }*/
   /**
    * parametros:
     {"titulo" : "",
     "descripcion_asignatura": "",
     "tipo":,
     "idLugar": ,
     "idEmpresa":,
     "Caracteristicas":[{
     "descripcion_estudiante":"",
    "paises":,
     "semestre":,
     "carID":2,
     "depId":,
     "Idiomas":[{"idIdioma":,"nivel":},{"idIdioma":,"nivel":}],
     "sytId":[{"idSyt":,"nivel":},{"idSyt":,"nivel":},{"idSyt":,"nivel":}],
     "hamId":[]
     },{
     "descripcion_estudiante":"",
     "paises":[],
     "semestre":,
     "carID":,
     "depId":[],
     "Idiomas":[{"idIdioma":,"nivel":},{"idIdioma":,"nivel":}],
     "sytId":[{"idSyt":,"nivel":},{"idSyt":,"nivel":},{"idSyt":,"nivel":}],
     "hamId":[],
    "preguntas":[{"titulo":""},{"titulo":""}]
     }]
     }
    * */
    @PostMapping("/AgregarDesafio_Practica")
    public List<HashMap<String,Object>> AgregarDesafio_Practica(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws IOException, MessagingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);

        String titulo = (params.containsKey(TITULO_ASI) && params.get(TITULO_ASI) != null && !params.get(TITULO_ASI).toString().isEmpty())
                ? params.get(TITULO_ASI).toString() : null;
        String descripcion_asi = (params.containsKey(DESCRIPCION_ASI) && params.get(DESCRIPCION_ASI) != null && !params.get(DESCRIPCION_ASI).toString().isEmpty())
                ? params.get(DESCRIPCION_ASI).toString() : null;
        Integer lugar 	= (params.containsKey(LUGAR) && params.get(LUGAR) != null && !params.get(LUGAR).toString().isEmpty())
                ? Integer.valueOf(params.get(LUGAR).toString()) : null;
        Integer tipo 	= (params.containsKey(TIPO_ASI) && params.get(TIPO_ASI) != null && !params.get(TIPO_ASI).toString().isEmpty())
                ? Integer.valueOf(params.get(TIPO_ASI).toString()) : null;
        Integer empId 	= (params.containsKey(ID_EMPRESA) && params.get(ID_EMPRESA) != null && !params.get(ID_EMPRESA).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_EMPRESA).toString()) : null;
        List<?> caracteristicas_Estudiante = (params.containsKey(CARACTERISTICAS) &&  params.get(CARACTERISTICAS) != null) ? UserService.convertObjectToList(params.get(CARACTERISTICAS)) : null;

                String descripcion  ="";
                Integer semestre    =0;
                Integer carrera     =0;
                Integer universidad =0;
                List<?> pais 	    =new ArrayList<>();
                List<?> deporte     =new ArrayList<>();
                List<?> idioma      =new ArrayList<>();
                List<?> softYTecn   =new ArrayList<>();
                List<?> hablidadesB =new ArrayList<>();
                List<?> preguntas   =new ArrayList<>();
        Asignatura asignatura = new Asignatura();
        Preguntas pregunta_asi = new Preguntas();
        List<Object> pregList = new ArrayList<>();
        HashMap<String,Object> listpreg = new HashMap<>();

        DetalleEstudiante estudiante = new DetalleEstudiante();

        List<HashMap<String,Object>> result = new ArrayList<>();
        HashMap<String,Object> listAsi = new HashMap<>();
        HashMap<String,Object> listDetEst = new HashMap<>();
        asignatura.setAsiTitulo(titulo);
        if (descripcion_asi!=null){
            asignatura.setAsiDescripcion(descripcion_asi);
        }
        asignatura.setAsiLugar(lugar);
        asignatura.setAsiTipo(tipo);
        asignatura.setEmpresa(empresaService.buscarEmpresaporId(empId));
       asignatura = asignaturaRepository.save(asignatura);
        listAsi.put("Asignatura",asignatura);
            if (caracteristicas_Estudiante.size()>0){
                for(int i=0; i<caracteristicas_Estudiante.size();i++){
                    LinkedHashMap caract = (LinkedHashMap) caracteristicas_Estudiante.get(i);
                    descripcion  = ((caract.containsKey(DESCRIPCION_EST))&&(caract.get(DESCRIPCION_EST))!=null) ? caract.get(DESCRIPCION_EST).toString() : null;
                    semestre    = ((caract.containsKey(SEMESTRE))&&(caract.get(SEMESTRE)!=null)) ? Integer.valueOf(caract.get(SEMESTRE).toString())  : null;
                    carrera     = Integer.valueOf(caract.get(CARRERA_ID).toString());
                    universidad = ((caract.containsKey(UNIVERSIDAD_ID))&&(caract.get(UNIVERSIDAD_ID)!=null)) ? Integer.valueOf(caract.get(UNIVERSIDAD_ID).toString()): null;
                    pais 	     = ((caract.containsKey(PAISES))&&(caract.get(PAISES)!=null)) ? UserService.convertObjectToList(caract.get(PAISES)): null  ;
                    deporte     = ((caract.containsKey(DEPORTE_ID))&&(caract.get(DEPORTE_ID)!=null)) ? UserService.convertObjectToList(caract.get(DEPORTE_ID)): null  ;
                    idioma      = ((caract.containsKey(IDIOMAS))&&(caract.get(IDIOMAS)!=null)) ? UserService.convertObjectToList(caract.get(IDIOMAS)): null  ;
                    softYTecn   = ((caract.containsKey(SOFTWARE_TECNOLOGIA))&&(caract.get(SOFTWARE_TECNOLOGIA)!=null)) ? UserService.convertObjectToList(caract.get(SOFTWARE_TECNOLOGIA)): null  ;
                    hablidadesB = ((caract.containsKey(HABILIDADES))&&(caract.get(HABILIDADES)!=null)) ? UserService.convertObjectToList(caract.get(HABILIDADES)): null  ;
                    preguntas   =((caract.containsKey(PREGUNTAS))&&(caract.get(PREGUNTAS)!=null)) ? UserService.convertObjectToList(caract.get(PREGUNTAS)):null;
                    estudiante = empresaService.guardarEstudiante(pais,deporte,empId,asignatura.getAsiId(),idioma,universidad,carrera,semestre,descripcion,softYTecn,hablidadesB,preguntas);
                    listDetEst.put("caracteristicas",estudiante);
                    listDetEst = new HashMap<>();
                    result.add(listDetEst);
                /*preguntas*/
                if (asignatura!=null && preguntas!=null && preguntas.size()>0) {
                    for (int j = 0; j < preguntas.size(); j++) {
                        HashMap<String, String> p = (HashMap<String, String>) preguntas.get(j);
                        pregunta_asi.setDecripcion(p.get("titulo"));
                        pregunta_asi.setAsignatura(asignatura);
                        pregunta_asi.setDetalleEstudiante(estudiante);
                        pregunta_asi = preguntasRepository.save(pregunta_asi);
                        pregList.add(pregunta_asi);
                        pregunta_asi = new Preguntas();
                    }

                    for (int j = 0; j < pregList.size(); j++) {
                        listpreg.put("preguntas " + (j+1), pregList.get(j));
                    }
                }
                    }
                }
            result.add(listAsi);
            result.add(listpreg);
        String asi = "";
        if (asignatura.getAsiTipo()==PRACTICAS){
            asi= "una Pasantia";
        }else if (asignatura.getAsiTipo()==DESAFIOS){
            asi= "un Desafio";
        }
            String msj ="Has subido "+ asi +" a tu perfil de MOSI, exitosamente";
            estudianteService.enviarEmail(asignatura.getEmpresa().getCorreo(),msj);
        return result;
    }

    @PostMapping("/verDesafio_Practica")
    public List<HashMap<String,Object>> verDesafio_Practica(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);

        Integer asignatura = (params.containsKey(ASIGNATURA) && params.get(ASIGNATURA) != null && !params.get(ASIGNATURA).toString().isEmpty())
                ? Integer.valueOf(params.get(ASIGNATURA).toString()) : null;


        List<HashMap<String,Object>> result = empresaService.verDetallePractDesaf(asignatura);

        return result;
    }
    /**Parametros:
     { "asignatura": 84}
     * */
    @PostMapping("/verPostulantes")
    public List<HashMap<String,Object>> verPostulantes(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);

        Integer asignatura = (params.containsKey(ASIGNATURA) && params.get(ASIGNATURA) != null && !params.get(ASIGNATURA).toString().isEmpty())
                ? Integer.valueOf(params.get(ASIGNATURA).toString()) : null;

        List<HashMap<String,Object>> result = empresaService.verPostulante(asignatura);


        return result;
    }
    @PostMapping("/verPerfilEstudiante")
    public HashMap<String,Object> verPerfilEstudiante(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException, ParseException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idEstudiante = (params.containsKey(ID_ESTUDIANTE) && params.get(ID_ESTUDIANTE) != null && !params.get(ID_ESTUDIANTE).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_ESTUDIANTE).toString()) : null;
        Integer asignatura = (params.containsKey(ASIGNATURA) && params.get(ASIGNATURA) != null && !params.get(ASIGNATURA).toString().isEmpty())
                ? Integer.valueOf(params.get(ASIGNATURA).toString()) : null;
        HashMap<String, Object> result = new HashMap<>();
        Postulaciones postulaciones = new Postulaciones();
        Estudiante perfil = estudianteRepository.findById(idEstudiante).get();
        Asignatura asig = asignaturaRepository.findByAsiId(asignatura);
        // HashMap<String,Object> Estudiante =estudianteService.consultaEstudiante()
        List<Object> caracteristicas = estudianteService.consultaCaracteristicas(perfil, null, ESTUDIANTE);
        Estudiante estudiante = ((Estudiante) caracteristicas.get(0));
        if (asig!=null) {
            postulaciones = postulacionesRepository.findByEstudianteAndAsignatura(estudiante, asig);
        }
        String nombres = ((Estudiante) caracteristicas.get(0)).getNombre().toString();
        String apellidos = ((Estudiante) caracteristicas.get(0)).getApellido().toString();
        Integer dia = null, mes = null, año = null;
        Period edad;
        if (estudiante.getFechaNac() != null) {
            año = Integer.valueOf((estudiante.getFechaNac().toString()).substring(0, 4));
            mes = Integer.valueOf((estudiante.getFechaNac().toString()).substring(4, 6));
            dia = Integer.valueOf((estudiante.getFechaNac().toString()).substring(6, 8));
            edad = Period.between(LocalDate.of(año, mes, dia), LocalDate.now());
            result.put("Fecha de NAcimiento", dia + "/" + mes + "/" + año);
            result.put("Edad", edad.getYears());
        }
        result.put("Nombre y Apellido", nombres + " " + apellidos);
        result.put("Pais", estudiante.getPais().getNombrePais());
        if (estudiante.getCiudad() != null) {
            result.put("Ciudad", estudiante.getCiudad().getCiuNombre());
        }
        String forma_de_trabajo = estudianteService.forma_de_trabajo(estudiante.getLugar());
        HashMap<String, Object> pregyResp = empresaService.buscarPreguntasYRespuestas(estudiante, postulaciones);
        if (estudiante.getDescripcion() != null) {
            result.put("Descripcion: ", estudiante.getDescripcion());
        }
        result.put("Forma de Trabajo", forma_de_trabajo);

        if ((postulaciones.getPosEstatus() == ACEPTADO)) {
            if (estudiante.getTelefono()!=null){
                result.put("Informacion de Contacto:", estudiante.getCorreo() + " " + estudiante.getCodigoPais() +" " +estudiante.getTelefono());
            }else{
                result.put("Informacion de Contacto:", estudiante.getCorreo());
            }
        } else {
            if (estudiante.getTelefono()!=null){
            result.put("Informacion de Contacto:", estudiante.getCorreo().substring(0, 4) + "*********.com -" + estudiante.getTelefono().substring(0, 4) + "*******");
            }else{
                result.put("Informacion de Contacto:", estudiante.getCorreo().substring(0, 4)+ "*********.com -");
            }
        }
        result.put("Semestre", estudiante.getSemestre());
        if (caracteristicas.get(1) != null) {
            result.put("Deportes", caracteristicas.get(1));
        }
        if (caracteristicas.get(2) != null) {
            result.put("Idiomas", caracteristicas.get(2));
            if (caracteristicas.get(3) != null) {
                result.put("Habilidades", caracteristicas.get(3));
            }
            if (caracteristicas.get(4) != null) {
                result.put("Pasatiempo", caracteristicas.get(4));
            }
            if (caracteristicas.get(5) != null) {
                result.put("Software y Tecnologias", caracteristicas.get(5));
            }
            if (pregyResp != null) {
                result.put("Preguntas:", pregyResp);
            }
        }
            return result;
    }

    /**
     {"idAsignatura" :63 ,
     "idEstudiante":108
     }
     * */
    @PostMapping("/seleccionarEstudiante")
    public HashMap<String,Object> seleccionarEstudiante(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws IOException, MessagingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idEstudiante = (params.containsKey(ID_ESTUDIANTE) && params.get(ID_ESTUDIANTE) != null && !params.get(ID_ESTUDIANTE).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_ESTUDIANTE).toString()) : null;
        Integer idAsignatura = (params.containsKey(ID_ASIGNATURA) && params.get(ID_ASIGNATURA) != null && !params.get(ID_ASIGNATURA).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_ASIGNATURA).toString()) : null;
        HashMap<String,Object> info_de_contacto_Estudiante = new HashMap<>();

        Postulaciones seleccionar = empresaService.cambiarEstatusPostulacion(idEstudiante,idAsignatura,ACEPTADO,null);

        if (seleccionar!=null){

            Estudiante estudiante=estudianteRepository.findById(idEstudiante).get();
            Asignatura asignatura = asignaturaRepository.findByAsiId(idAsignatura);
            Boolean not = estudianteService.notificar(asignatura.getEmpresa().getId(),asignatura,TITULO_NOTIFICACION_SELECCION,ACEPTADO,idEstudiante,EMPRESA);
            info_de_contacto_Estudiante.put("Telefono: ", estudiante.getCodigoPais()+"-"+estudiante.getTelefono());
            info_de_contacto_Estudiante.put("Correo: ", estudiante.getCorreo());
            info_de_contacto_Estudiante.put("Nombre: ", estudiante.getNombre()+" "+ estudiante.getApellido());
            /*envio correo de notificacion*/
            String mensaje_est = DETALLE_EMAIL_SELECCIONAR_ESTUDIANTE + asignatura.getEmpresa().getNombre() +
                    "\n Puedes rechazar la oferta haciendo click al siguiente enlace: http://localhost:8080/rechazarPostulacion?idPos=" + seleccionar.getPosId();
            estudianteService.enviarEmail(estudiante.getCorreo(),mensaje_est);
            String mensaje_emp = DETALLE_EMAIL_SELECCIONAR_EMPRESA +estudiante.getNombre()+ " "+estudiante.getApellido()
                    + ", ahora puedes comunicarte con él , sus datos de contacto son: Correo: " + estudiante.getCorreo()+ " Telefono: " + estudiante.getTelefono();
            estudianteService.enviarEmail(asignatura.getEmpresa().getCorreo(), mensaje_emp);

        }
        return info_de_contacto_Estudiante;
    }
    /**Parametros**
     {"idAsignatura":}
     * */
    @PostMapping("/verEstudiantesSeleccionados")
    public List<Postulaciones> verEstudiantesSeleccionados(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException, ParseException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idAsignatura = (params.containsKey(ID_ASIGNATURA) && params.get(ID_ASIGNATURA) != null && !params.get(ID_ASIGNATURA).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_ASIGNATURA).toString()) : null;

        List<Postulaciones> listaEstudiantesSeleccionados = empresaService.verEstudiantesSeleccionados(idAsignatura);

        return listaEstudiantesSeleccionados;
    }
    /**Parametros**
     {"idAsignatura":}
     * */
    @PostMapping("/sugerirEstudiantes")
    public List<HashMap<String,Object>> sugerirEstudiantes(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException, ParseException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idAsignatura = (params.containsKey(ID_ASIGNATURA) && params.get(ID_ASIGNATURA) != null && !params.get(ID_ASIGNATURA).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_ASIGNATURA).toString()) : null;

        List<HashMap<String,Object>> result = empresaService.sugerirEstudiante(idAsignatura);

        return result;

    }
    /**
     *Parametros:
     {"idPostulacion" :74}
    */
    @PostMapping("/rechazarEstudiante")
    public String rechazarEstudiante(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws IOException, MessagingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer portulacion = (params.containsKey(POSTULACION) && params.get(POSTULACION) != null && !params.get(POSTULACION).toString().isEmpty())
                ? Integer.valueOf(params.get(POSTULACION).toString()) : null;


        String resp =  empresaService.rechazarEstudiante(portulacion);
        return resp;
    }
    /**PARAMETROS:
     {"descripcion_empresa": " ",
     "rubro":4 ,
     "ubicacion": ,
     "paisId":4 ,
     "sitioWeb":"",
     "codigo_pais": "",
     "correo": "epacompania@gmail.com",
     "nombreEmpresa":"EPA,C.A" ,
     "razonSocial":"",
     "telefono":"",
     "mision":"",
     "vision":"",
     "idUser":22}
     * */
    @PostMapping("modificarPerfilEmpresa")
    public Empresa modificarPerfilEmpresa(HttpServletRequest request, HttpServletResponse response,
                                                      @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String descripcion 	= (params.containsKey(DESCRIPCION_EMP) && params.get(DESCRIPCION_EMP) != null) ? params.get(DESCRIPCION_EMP).toString() : null;
        Integer rubro 	= (params.containsKey(RUBRO) && params.get(RUBRO) != null) ? Integer.valueOf(params.get(RUBRO).toString()) : null;
        String ubicacion 	= (params.containsKey(UBICACION_EMP) && params.get(UBICACION_EMP) != null && !params.get(UBICACION_EMP).toString().isEmpty())
                ? params.get(UBICACION_EMP).toString() : null;
        Integer pais 	= (params.containsKey(PAIS_ID) && params.get(PAIS_ID) != null && !params.get(PAIS_ID).toString().isEmpty())
                ? Integer.valueOf(params.get(PAIS_ID).toString()) : null;
        String sitioW 	= (params.containsKey(SITIO_WEB_EMP) && params.get(SITIO_WEB_EMP) != null && !params.get(SITIO_WEB_EMP).toString().isEmpty())
                ? params.get(SITIO_WEB_EMP).toString() : null;
        String codigoPais 	= (params.containsKey(CODIGO_PAIS) && params.get(CODIGO_PAIS) != null && !params.get(CODIGO_PAIS).toString().isEmpty())
                ? params.get(CODIGO_PAIS).toString() : null;
        String correo  = (params.containsKey(CORREO) && params.get(CORREO) != null && !params.get(CORREO).toString().isEmpty()) ? params.get(CORREO).toString() : null;
        String nombre 	= (params.containsKey(NOMBRE_EMP) && params.get(NOMBRE_EMP) != null && !params.get(NOMBRE_EMP).toString().isEmpty())
                ? params.get(NOMBRE_EMP).toString() : null;
        String razonSocial = (params.containsKey(RAZON_SOCIAL_EMP) && params.get(RAZON_SOCIAL_EMP) != null && !params.get(RAZON_SOCIAL_EMP).toString().isEmpty())
                ? params.get(RAZON_SOCIAL_EMP).toString() : null;
        String telefono = (params.containsKey(TELEFONO) &&  params.get(TELEFONO) != null) ? params.get(TELEFONO).toString() : null;
        String mision = (params.containsKey(MISION_EMP) &&  params.get(MISION_EMP) != null) ? params.get(MISION_EMP).toString() : null;
        String vision = (params.containsKey(VISION) && params.get(VISION) != null && !params.get(VISION).toString().isEmpty())
                ? params.get(VISION).toString() : null;
        Integer usuId= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty() ? Integer.valueOf(params.get(ID_USER).toString()) : null );
        Empresa empresa = empresaService.actualizarDatosEmpresa(descripcion,rubro,ubicacion, pais,sitioW,codigoPais,correo,nombre,razonSocial,telefono,mision,
                vision,usuId);
        return empresa;
    }

    }
