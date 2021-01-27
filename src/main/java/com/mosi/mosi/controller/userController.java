package com.mosi.mosi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosi.mosi.bean.*;
import com.mosi.mosi.constantes.CustomLoggerLevelEnum;
import com.mosi.mosi.repository.*;
import com.mosi.mosi.service.EstudianteService;
import com.mosi.mosi.service.UserService;
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
import java.util.*;

import static com.mosi.mosi.constantes.constante.*;


@RestController
public class userController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    EstudianteEspecialidadRepository estudianteEspecialidadRepository;
    @Autowired
    EspecialidadRepository especialidadRepository;
    @Autowired
    DeporteMaestroRepository deporteMaestroRepository;
    @Autowired
    UniversidadEstudianteRepository universidadEstudianteRepository;
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
    PasionesMaestroRepository pasionesMaestroRepository;
    @Autowired
    InvestigacionesRepository investigacionesRepository;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private UserService userService;
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
    @PostMapping("/guardarPerfilEstudiante")
    public Estudiante guardarPerfilEstudiante(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {

        Estudiante estudiante = new Estudiante();

        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String nombresEstudiante 	= (params.containsKey(NOMBRES) && params.get(NOMBRES) != null) ? params.get(NOMBRES).toString() : null;
        String apellidosEstudiante 	= (params.containsKey(APELLIDOS) && params.get(APELLIDOS) != null) ? params.get(APELLIDOS).toString() : null;
        Integer fechaNac 	= (params.containsKey(FECHA) && params.get(FECHA) != null && !params.get(FECHA).toString().isEmpty()) ? Integer.valueOf(params.get(FECHA).toString()) : null;
        Integer pais 	= (params.containsKey(PAIS_ID) && params.get(PAIS_ID) != null && !params.get(PAIS_ID).toString().isEmpty()) ? Integer.valueOf(params.get(PAIS_ID).toString()) : null;
        Integer usuario 	= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty()) ? Integer.valueOf(params.get(ID_USER).toString()) : null;
        Integer semestre = (params.containsKey(SEMESTRE) && params.get(SEMESTRE) != null && !params.get(SEMESTRE).toString().isEmpty()) ? Integer.valueOf(params.get(SEMESTRE).toString()) : null;
        List<?> deporte = (params.containsKey(DEPORTE_ID) &&  params.get(DEPORTE_ID) != null) ? UserService.convertObjectToList(params.get(DEPORTE_ID)) : null;
        Integer universidad = (params.containsKey(UNIVERSIDAD_ID) && params.get(UNIVERSIDAD_ID) != null && !params.get(UNIVERSIDAD_ID).toString().isEmpty()) ? Integer.valueOf(params.get(UNIVERSIDAD_ID).toString()) : null;
        List<?> idioma = (params.containsKey(IDIOMA_ID) &&  params.get(IDIOMA_ID) != null) ? UserService.convertObjectToList(params.get(IDIOMA_ID)) : null;
        Integer especialidad = (params.containsKey(ESPECIALIDAD_ID) && params.get(ESPECIALIDAD_ID) != null && !params.get(ESPECIALIDAD_ID).toString().isEmpty()) ? Integer.valueOf(params.get(ESPECIALIDAD_ID).toString()) : null;
        Integer carrera = (params.containsKey(CARRERA_ID) && params.get(CARRERA_ID) != null && !params.get(CARRERA_ID).toString().isEmpty()) ? Integer.valueOf(params.get(CARRERA_ID).toString()) : null;
        String pasion = (params.containsKey(PASION) && params.get(PASION) != null && !params.get(PASION).toString().isEmpty()) ? params.get(PASION).toString() : null;
        List<?> pasatiempo = (params.containsKey(PASATIEMPO) &&  params.get(PASATIEMPO) != null) ? UserService.convertObjectToList(params.get(PASATIEMPO)) : null;

        estudiante = estudianteService.guardarEstudiante(nombresEstudiante,apellidosEstudiante,fechaNac,pais,deporte,
                idioma,universidad,especialidad,carrera, usuario,semestre,pasatiempo,pasion);

        return estudiante;

    }
    /*
    * Metodo Para Obtener Perfil del Estudiante
    * */
    @PostMapping("/consultarEstudiante")
    public  List<List<HashMap<String, String>>> consultarEstudiante(
            @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {

        IdiomaMaestro idi =new IdiomaMaestro();
        EstudianteEspecialidad esp =new EstudianteEspecialidad();
        CarreraUniversitariaMaestro carr =new CarreraUniversitariaMaestro();
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer usuario 	= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty()) ? Integer.valueOf(params.get(ID_USER).toString()) : null;

        List<List<HashMap<String, String>>> perfilEstudianteDetallado = estudianteService.consulta(usuario) ;

        return perfilEstudianteDetallado;

    }


    @PostMapping("signInEstudiante")
    public Object signInEstudiante(HttpServletRequest request, HttpServletResponse response,
                                           @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String nombreusuario 	= (params.containsKey(USERNAME) && params.get(USERNAME) != null && !params.get(USERNAME).toString().isEmpty()) ?params.get(USERNAME).toString() : null;
        String clave 	= (params.containsKey(CLAVE) && params.get(CLAVE) != null && !params.get(CLAVE).toString().isEmpty()) ?params.get(CLAVE).toString() : null;
        String email 	= (params.containsKey(EMAIL) && params.get(EMAIL) != null && !params.get(EMAIL).toString().isEmpty()) ?params.get(EMAIL).toString() : null;

        Object objectResult = null;
        if ((!email.isEmpty()) && (!nombreusuario.isEmpty()) && (!clave.isEmpty())){
            try{
                objectResult = userService.signIn(nombreusuario, clave, email);


            }catch(HttpClientErrorException e) {
                log.error("Se produjo un error al procesar la solicitud. (" + e.getMessage() + ")");
                log.log(CustomLoggerLevelEnum.EXCEPTION.LEVEL(), "Se produjo un error al procesar la solicitud", e);
                objectResult = EstudianteService.commonErrorMessage(response);
            }
        }else
            objectResult = EstudianteService.commonErrorMessage(response);



    return objectResult;
    }
    @PostMapping("buscar_Practica_desafio")
    public List<List<HashMap<String, String>>> buscarPracticaDesafio(
            @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer usuario = (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty()) ? Integer.valueOf(params.get(ID_USER).toString()) : null;
        Integer asignatura = (params.containsKey(ASIGNATURA) && params.get(ASIGNATURA) != null && !params.get(ASIGNATURA).toString().isEmpty()) ? Integer.valueOf(params.get(ASIGNATURA).toString()) : null;

        List<List<HashMap<String, String>>> perfil = estudianteService.consulta(usuario);

        Integer idEst = Integer.valueOf(perfil.get(0).get(0).get("idEstudiante"));
        Integer idPais = Integer.valueOf(perfil.get(0).get(0).get("idPais"));
        Integer idCar = Integer.valueOf(perfil.get(0).get(0).get("Carrera"));
        Integer idEsp = Integer.valueOf(perfil.get(0).get(0).get("Especialidad"));
        Integer semestre = Integer.valueOf(perfil.get(0).get(0).get("Semestre"));

        /*busco deporte*/
        List<Integer> idsDeporte = new ArrayList<>();
        Integer d = perfil.get(1).size();
        for (int i = 0; i < d; i++) {
            idsDeporte.add(Integer.valueOf(perfil.get(1).get(0).get("id")));
        }
        /**idioma*/
        List<Integer> idsIdioma = new ArrayList<>();
        Integer a = perfil.get(2).size();
        for (int i = 0; i < a; i++) {
            idsIdioma.add(Integer.valueOf(perfil.get(2).get(0).get("id")));
        }

        /***/
        /**busqueda por carrera y pais*/
        Integer caracteristica = 0;

        List<DetalleEstudiante> det_estudiante = detalleEstudianteRepository.consultar_estudiantes_empresa(idCar,idPais,idEsp,semestre,asignatura);
        HashMap<String,String> detalleEstudianteEmp = new HashMap<>();
        List<Integer> idsDet = new ArrayList<>();
        HashMap<String,String> listaEspSemCarr = new HashMap<>();

        for (int i = 0; i < det_estudiante.size(); i++) {
             detalleEstudianteEmp = this.buildPerfildetalleEstudiante(det_estudiante);
         String g = detalleEstudianteEmp.get("id");
            idsDet.add(Integer.valueOf(g));
            detalleEstudianteEmp = new HashMap<>();
        }
        /*listado deporte empresa*/
        List<Object[]> dep_Empresa = deporteMaestroRepository.consultar_deporte_estudiante_empresa(idsDet);
        List<Object[]> idi_Empresa = idiomaMaestroRepository.consultar_idioma_estudiante_empresa(idsDet);
        List<List<HashMap<String,String>>> perfilC = new ArrayList();
        /*List<HashMap<String,String>> perfilA*/
       /* for (int i = 0; i < det_estudiante.size(); i++) {//contruir array de detalle de estudinte
          //  perfilC.add();
            for (int w=0; w<dep_Empresa.size();w++){ //deporte
                String x = dep_Empresa.get(w)[0].toString();
                if (det_estudiante.get(i).getId().toString().equals(x)) {
                    perfilC.add(dep_Empresa.get(w));
                }
            }
            for (int e=0; e<dep_Empresa.size();e++){ //idioma
                String x = idi_Empresa.get(e)[0].toString();
                if (det_estudiante.get(i).getId().toString().equals(x)) {
                    perfilC.add(idi_Empresa.get(e));
                }
            }
        }*/

       /* for (int i = 0; i<resul.size(); i++){
            depoList.put("id",resul.get(i)[0].toString());
            depoList.put("Nombre",resul.get(i)[1].toString());
            resulDepo.add(depoList);
            depoList = new HashMap<String, String>();
        }*/
      /*  List<List<HashMap<String, String>>> listEmpresasDetalle= new ArrayList<>();
        List<List<List<HashMap<String, String>>>> ListaEmpresas = new ArrayList<>();
        HashMap<String, String> listEmpD = new HashMap<String, String>();
        HashMap<String, String> listEmpI = new HashMap<String, String>();

        List<HashMap<String, String>> list_Emp = new ArrayList<>();
        List<List<HashMap<String,String>>> emp_list = new ArrayList<>();

        for (int i = 0; i < det_estudiante.size(); i++) {
            List<Integer[]> dep_estudiante = deporteMaestroRepository.consultar_deporte_estudiante_empresa(idsDet);
            if (dep_estudiante.size()>0 && dep_estudiante!=null) {
                caracteristica =+ 1;
                for (int j = 0; j < dep_estudiante.size(); j++) {
                    listEmpD.put("idDep" + j, dep_estudiante.get(j)[i].toString());
                    list_Emp.add(listEmpD);
                    listEmpD = new HashMap<>();

                }
                emp_list.add(list_Emp);
            }
            list_Emp = new ArrayList<>();
            List<Integer[]> idiId_estudiante = idiomaMaestroRepository.consultar_idioma_estudiante_empresa(idsDet);
            if (idiId_estudiante.size()>0 && idiId_estudiante!=null) {
                caracteristica =+ 1;
                for (int j = 0; j < idiId_estudiante.size(); j++) {
                    listEmpI.put("idIdi" + j, idiId_estudiante.get(j)[i].toString());
                    list_Emp.add(listEmpI);
                    listEmpI = new HashMap<>();

                }
                emp_list.add(list_Emp);
            }
            list_Emp = new ArrayList<>();
            listaEspSemCarr.put("espId",det_estudiante.get(0)[1].toString());
            listaEspSemCarr.put("semestre",det_estudiante.get(0)[2].toString());
            list_Emp.add(listaEspSemCarr);
            listEmpresasDetalle.add(list_Emp);
            listEmpresasDetalle.add(emp_list.get(0));
            listEmpresasDetalle.add(emp_list.get(1));
            listaEspSemCarr = new HashMap<>();
            ListaEmpresas.add(listEmpresasDetalle);
        }*/
        /*Integer acum =0;
        Boolean BandSemestre = false; Boolean BandEspecialidad = false; Boolean BandIdioma = false; Boolean BandDeporte = false; Integer puntaje = 0;
        for (int k=0; k<ListaEmpresas.size();k++){
            String semestreEmp = ListaEmpresas.get(k).get(0).get(0).get("semestre");
            String semestreEst = perfil.get(0).get(0).get("Semestre");
            if (semestreEmp==semestreEst){
                BandSemestre = true;
                acum =+1;
            }
            String especialidadEmp = ListaEmpresas.get(k).get(0).get(0).get("espId");
            String especialidadEst =perfil.get(4).get(0).get("id");
            if (especialidadEmp==especialidadEst){//especialidad
                BandEspecialidad = true;
                acum =+1;
            }
            //deporte
            for (int l=0;l<ListaEmpresas.get(k).get(1).size();l++){
                for (int m =0;m<perfil.get(1).size();m++){
                    if (ListaEmpresas.get(k).get(1).get(l).get("idDepo")==perfil.get(1).get(m).get("id")){
                        if (ListaEmpresas.get(k).get(2).get(l).get("idIdi")==perfil.get(3).get(m).get("id")){
                            BandIdioma = true;
                        }
                        if (BandIdioma)
                            acum =+1;
                    }
                }
            }
        }*/
        return perfil;
    }
    public  HashMap<String,String> buildPerfildetalleEstudiante(List<DetalleEstudiante> perfil_empresa){
        HashMap<String,String> detalle = new HashMap<>();
        detalle.put("id",perfil_empresa.get(0).getId().toString());
        detalle.put("descripcion",perfil_empresa.get(0).getDescripcion().toString());
        detalle.put("espId",perfil_empresa.get(0).getDetIdEsp().toString());
        detalle.put("asiID",perfil_empresa.get(0).getAsignatura().toString());
        detalle.put("empID",perfil_empresa.get(0).getIdEmp().toString());
        detalle.put("paiId",perfil_empresa.get(0).getIdpai().toString());
        detalle.put("det_sem",perfil_empresa.get(0).getSemestre().toString());
        detalle.put("carId",perfil_empresa.get(0).getIdCar().toString());
        detalle.put("uniID",perfil_empresa.get(0).getIdUni().toString());

        return detalle;
    }

    @PostMapping("userLogin")
    public HashMap<String,String> loginUser(HttpServletRequest request, HttpServletResponse response,
                                            @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        HashMap<String,String> objectResult = new HashMap<>();
        String nombreusuario 	= (params.containsKey(USERNAME) && params.get(USERNAME) != null) ? params.get(USERNAME).toString() : null;
        String clave = (params.containsKey(CLAVE) && params.get(CLAVE) != null) ? params.get(CLAVE).toString() : null;

        if ((!nombreusuario.isEmpty()) && (!clave.isEmpty())){
            try{
                 objectResult = userService.userlogin(nombreusuario,clave);



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
    @PostMapping("consultarEspecialidades")
    public List<Especialidad> consultarEspecialidades(HttpServletRequest request, HttpServletResponse response) {
        List<Especialidad> listEspecialidad= especialidadRepository.findAllByIdGreaterThan(0);
        return listEspecialidad;
    }
    @PostMapping("consultarDeportes")
    public List<Deporte> consultarDeporte(HttpServletRequest request, HttpServletResponse response) {
        List<Deporte> listDeporte= deporteRepository.findAllByIdGreaterThan(0);
        return listDeporte;
    }
    @PostMapping("consultarIdiomas")
    public List<Idioma> consultarIdioma(HttpServletRequest request, HttpServletResponse response) {
        List<Idioma> listIdioma= idiomaRepository.findAllByIdGreaterThan(0);
        return listIdioma;
    }
    @PostMapping("consultarCarreras")
    public List<Carrera> consultarCarrera(HttpServletRequest request, HttpServletResponse response) {
        List<Carrera> ListCarrera= carreraRepository.findAllByIdGreaterThan(0);
        return ListCarrera;
    }
    @PostMapping("consultarUniversidades")
    public List<Universidad> consultarUniversidad(HttpServletRequest request, HttpServletResponse response) {
        List<Universidad> ListUniversidad= universidadRepository.findAllByIdGreaterThan(0);
        return ListUniversidad;
    }
    @PostMapping("consultarPaises")
    public List<Paises> consultarPaises(HttpServletRequest request, HttpServletResponse response) {
        List<Paises> ListPaises= paisesRepository.findAllByIdGreaterThan(0);
        return ListPaises;
    }
    @PostMapping("consultarPasatiempos")
    public List<Pasatiempo> consultarPasatiempo(HttpServletRequest request, HttpServletResponse response) {
        List<Pasatiempo> ListPasatiempo= pasatiempoRepository.findAllByIdGreaterThan(0);
        return ListPasatiempo;
    }
    @PostMapping("postularEstudiante")
    public String postularEstudiante(HttpServletRequest request, HttpServletResponse response,
                                               @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String resp = "";
        Integer idAsignatura 	= (params.containsKey(ID_ASIGNATURA) && params.get(ID_ASIGNATURA) != null) ? Integer.valueOf(params.get(ID_ASIGNATURA).toString()): null;
        Integer idEmpresa = (params.containsKey(ID_EMPRESA) && params.get(ID_EMPRESA) != null) ? Integer.valueOf(params.get(ID_EMPRESA).toString()) : null;
        Integer idEstudiante = (params.containsKey(ID_ESTUDIANTE) && params.get(ID_ESTUDIANTE) != null) ? Integer.valueOf(params.get(ID_ESTUDIANTE).toString()) : null;

        if (idAsignatura!=null && idEmpresa!=null && idEstudiante!=null){
           resp = estudianteService.postular(idAsignatura,idEmpresa,idEmpresa);
        }
        return resp;
    }
    @PostMapping("detalleAsignatura")
    public HashMap<String,String> detalleAsignatura(HttpServletRequest request, HttpServletResponse response,
                                     @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idAsignatura 	= (params.containsKey(ID_ASIGNATURA) && params.get(ID_ASIGNATURA) != null) ? Integer.valueOf(params.get(ID_ASIGNATURA).toString()): null;
        HashMap<String,String> detAsig =  estudianteService.mapearDetalleAsignatura(asignaturaRepository.consultaDetalleAsignatura(idAsignatura));


        return detAsig;
    }
    @PostMapping("cambiarPerfilPrincipal")
    public Estudiante cambiarPerfilPrincipal(HttpServletRequest request, HttpServletResponse response,
                                                    @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer usuario 	= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty()) ? Integer.valueOf(params.get(ID_USER).toString()) : null;
        Integer idEst 	= (params.containsKey(ID_ESTUDIANTE) && params.get(ID_ESTUDIANTE) != null && !params.get(ID_ESTUDIANTE).toString().isEmpty()) ? Integer.valueOf(params.get(ID_ESTUDIANTE).toString()) : null;

        // primero consulto perfil estudiante activo actualmente y cambio estatus a inactivo
        Estudiante perfilPrinc =estudianteRepository.consultaPerfilActivo(usuario);
                Integer idEstudianteActivo = perfilPrinc.getId();
          estudianteRepository.updateStatusPerfil(idEstudianteActivo,INACTIVO);
          //cambio estatus de perfil estudiante seleccionado a Activo
        Integer resp = estudianteRepository.updateStatusPerfil(idEst,ACTIVO);
        perfilPrinc = estudianteRepository.consultaPerfilActivo(usuario);
        String respf = "Cambio de perfil Exitoso";

        return perfilPrinc;
    }

}

