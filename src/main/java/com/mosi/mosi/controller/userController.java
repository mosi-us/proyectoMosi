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
    CiudadesRepository ciudadesRepository;
    @Autowired
    SoftwareTecnologiaRepository softwareTecnologiaRepository;
    @Autowired
    HabilidadesBlandasRepository habilidadesBlandasRepository;
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
        Integer fechaNac 	= (params.containsKey(FECHA) && params.get(FECHA) != null && !params.get(FECHA).toString().isEmpty())
                ? Integer.valueOf(params.get(FECHA).toString()) : null;
        Integer pais 	= (params.containsKey(PAIS_ID) && params.get(PAIS_ID) != null && !params.get(PAIS_ID).toString().isEmpty())
                ? Integer.valueOf(params.get(PAIS_ID).toString()) : null;
        Integer ciudad 	= (params.containsKey(CIUDAD) && params.get(CIUDAD) != null && !params.get(CIUDAD).toString().isEmpty())
                ? Integer.valueOf(params.get(CIUDAD).toString()) : null;
        String telefono 	= (params.containsKey(TELEFONO) && params.get(TELEFONO) != null && !params.get(TELEFONO).toString().isEmpty())
                ? params.get(TELEFONO).toString() : null;
        String codigoPais 	= (params.containsKey(CODIGO_PAIS) && params.get(CODIGO_PAIS) != null && !params.get(CODIGO_PAIS).toString().isEmpty())
                ? params.get(CODIGO_PAIS).toString() : null;
        String correo  = (params.containsKey(CORREO) && params.get(CORREO) != null && !params.get(CORREO).toString().isEmpty()) ? params.get(CORREO).toString() : null;
        Integer usuario 	= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_USER).toString()) : null;
        Integer semestre = (params.containsKey(SEMESTRE) && params.get(SEMESTRE) != null && !params.get(SEMESTRE).toString().isEmpty())
                ? Integer.valueOf(params.get(SEMESTRE).toString()) : null;
        List<?> deporte = (params.containsKey(DEPORTE_ID) &&  params.get(DEPORTE_ID) != null) ? UserService.convertObjectToList(params.get(DEPORTE_ID)) : null;
        Integer universidad = (params.containsKey(UNIVERSIDAD_ID) && params.get(UNIVERSIDAD_ID) != null && !params.get(UNIVERSIDAD_ID).toString().isEmpty())
                ? Integer.valueOf(params.get(UNIVERSIDAD_ID).toString()) : null;
        List<?> idioma = (params.containsKey(IDIOMAS) &&  params.get(IDIOMAS) != null) ? UserService.convertObjectToList(params.get(IDIOMAS)) : null;
        Integer carrera = (params.containsKey(CARRERA_ID) && params.get(CARRERA_ID) != null && !params.get(CARRERA_ID).toString().isEmpty())
                ? Integer.valueOf(params.get(CARRERA_ID).toString()) : null;
        String descripcion = (params.containsKey(DESCRIPCION_EST) && params.get(DESCRIPCION_EST) != null && !params.get(DESCRIPCION_EST).toString().isEmpty())
                ? params.get(DESCRIPCION_EST).toString() : null;
        List<?> pasatiempo = (params.containsKey(PASATIEMPO) &&  params.get(PASATIEMPO) != null) ? UserService.convertObjectToList(params.get(PASATIEMPO)) : null;
        List<?> softYTecn = (params.containsKey(SOFTWARE_TECNOLOGIA) &&  params.get(SOFTWARE_TECNOLOGIA) != null) ? UserService.convertObjectToList(params.get(SOFTWARE_TECNOLOGIA)) : null;
        List<?> hablidadesB = (params.containsKey(HABILIDADES) &&  params.get(HABILIDADES) != null) ? UserService.convertObjectToList(params.get(HABILIDADES)) : null;

        estudiante = estudianteService.guardarEstudiante(nombresEstudiante,apellidosEstudiante,fechaNac,pais,ciudad,telefono,codigoPais,correo,deporte,
                idioma,universidad,carrera, usuario,semestre,pasatiempo,descripcion,softYTecn,hablidadesB);

        return estudiante;

    }
    /*
    * Metodo Para Obtener Perfil del Estudiante
    * */
    @PostMapping("/consultarEstudiante")
    public  List<List<HashMap<String, String>>> consultarEstudiante(
            @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {

        IdiomaMaestro idi =new IdiomaMaestro();
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
    public List<HashMap<String, Object>> buscarPracticaDesafio(
            @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer usuario = (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty()) ?
                Integer.valueOf(params.get(ID_USER).toString()) : null;
        String asignatura = (params.containsKey(ASIGNATURA) && params.get(ASIGNATURA) != null && !params.get(ASIGNATURA).toString().isEmpty()) ?
                params.get(ASIGNATURA).toString() : null;
        List<HashMap<String,Object>> result = new ArrayList<>();

        List<List<HashMap<String, String>>> perfil = estudianteService.consulta(usuario);

        Integer idEst = Integer.valueOf(perfil.get(0).get(0).get("idEstudiante"));
        Integer idPais = Integer.valueOf(perfil.get(0).get(0).get("idPais"));
        Integer idCar = Integer.valueOf(perfil.get(0).get(0).get("idCarrera"));
        Integer semestre = Integer.valueOf(perfil.get(0).get(0).get("semestre"));
        Integer idUni = Integer.valueOf(perfil.get(0).get(0).get("idUni"));


        /**busqueda por carrera y pais*/
        Integer caracteristica = 0;
        Integer idAsi =  0;
        if(asignatura.equals("Practicas") || (asignatura.equals("practicas"))){
            idAsi = PRACTICAS;
        }else if (asignatura.equals("Desafios") || (asignatura.equals("desafios"))) {
            idAsi = DESAFIOS;
        }
        List<DetalleEstudiante> det_estudiante = detalleEstudianteRepository.consultar_estudiantes_empresa(idCar,idPais,semestre,idAsi);
        List<List<HashMap<String,String>>> resulDetalleEstudiante = new ArrayList<>();
        List<HashMap<String,Object>> resulDetalleEstudiantes = estudianteService.listarDetalleEstudiantes(det_estudiante);

        List<List<HashMap<String,Object>>> listadoOrdenadoDetalle = new ArrayList<>();
        List<HashMap<String,Object>> list_por_items = new ArrayList<>();
        HashMap<String,Object> afinidad = new HashMap<>();

       // ya tengo el listado armado ahora se procede a comparar
        Integer atributosEmpresa = 2; // inicia en 2 porque el valor de pais y carrera estan incluidos
        Integer atributosEstudiante = 2;
        Integer semestreEmpresa = 0;
        Integer semestreEstudiante = 0;
        Integer uniId_empresa = 0;


        for (int e=0;e<resulDetalleEstudiantes.size();e++){
            /*LISTADO DE PRACTICAS/ DESAFIOS*/
            DetalleEstudiante estEmpresa = (DetalleEstudiante) resulDetalleEstudiantes.get(e).get("estudiante");
            Object[] listAsig =asignaturaRepository.consultaDetalleAsignatura(estEmpresa.getAsignatura());
            HashMap<String,Object> asignaturaEmpresa =new HashMap<>();
            asignaturaEmpresa.put("asignatura",listAsig);
            result.add(asignaturaEmpresa);
            if ((estEmpresa.getSemestre()!=null)) {
                 semestreEmpresa = Integer.valueOf(estEmpresa.getSemestre());
            }
            if (estEmpresa.getIdUni()!=null) {
                uniId_empresa = Integer.valueOf(estEmpresa.getIdUni());
            }
            Integer empId = estEmpresa.getIdEmp();
            Integer lugarEmp = detalleEstudianteRepository.consultaLugarTrabajo(empId);
            Integer lugarEst = Integer.valueOf(perfil.get(0).get(0).get("lugar"));

            HashMap<String,String> idiomasEmpresa = (HashMap<String, String>) resulDetalleEstudiantes.get(e).get("idioma");
            List<HashMap<String,String>> idiomasEstudiante = perfil.get(2);

           HashMap<String,String> deportesEmpresa =(HashMap<String, String>) resulDetalleEstudiantes.get(e).get("deporte");
            List<HashMap<String,String>> deportesEstudiante = perfil.get(1);
            HashMap<String,String> habilidadesEmpresa = (HashMap<String, String>) resulDetalleEstudiantes.get(e).get("habilidades");
            List<HashMap<String,String>> habilidadesEstudiante = perfil.get(4);
            HashMap<String,String> sytEmpresa = (HashMap<String, String>) resulDetalleEstudiantes.get(e).get("softYtecn");
            List<HashMap<String,String>> sytEstudiante = perfil.get(5);;
            if ((semestre==semestreEmpresa) || (semestre>semestreEmpresa) ){
                atributosEmpresa = atributosEmpresa + 1;
                atributosEstudiante = atributosEstudiante +1;
            }
            if (uniId_empresa==idUni){
                atributosEmpresa = atributosEmpresa + 1;
                atributosEstudiante = atributosEstudiante +1;
            }
            if (lugarEst == lugarEmp){
                atributosEmpresa = atributosEmpresa + 1;
                atributosEstudiante = atributosEstudiante +1;
            }

            int size_idioma = idiomasEmpresa.size()/2;
           for (int z=0;z<size_idioma;z++){ // idiomas
                Integer idiomaEmpresa_list = Integer.valueOf(idiomasEmpresa.get("idIdi" +(z+1)));
                Integer nivelIdiomaEmpresa = Integer.valueOf(idiomasEmpresa.get("nivel"+(z+1)));
               atributosEmpresa = atributosEmpresa + 1;
                for (int x= 0;x<idiomasEstudiante.size();x++) {
                    Integer idiomasEstudiante_list=Integer.valueOf( idiomasEstudiante.get(x).get("id"));
                    Integer nivelIdiomaEstudiante = Integer.valueOf(idiomasEstudiante.get(x).get("nivel"));
                    if ((idiomaEmpresa_list ==idiomasEstudiante_list)&&((nivelIdiomaEstudiante==nivelIdiomaEmpresa)||(nivelIdiomaEstudiante>nivelIdiomaEmpresa))) {
                        atributosEstudiante = atributosEstudiante + 1;
                    }
                }
            }
            int size_deporte = deportesEmpresa.size();
            for (int z=0;z<size_deporte;z++){ // deporte
                Integer DeporteEmpresa_list = Integer.valueOf(deportesEmpresa.get("idDep" +(z+1)));
                atributosEmpresa = atributosEmpresa + 1;
                for (int x= 0;x<size_deporte;x++) {
                      Integer DeporteEstudiante_list=Integer.valueOf( deportesEstudiante.get(x).get("id"));
                    if (DeporteEmpresa_list ==DeporteEstudiante_list) {
                        atributosEstudiante = atributosEstudiante + 1;
                    }
                }
            }
            int size_habilidades = habilidadesEmpresa.size();
            for (int z=0;z<size_habilidades;z++){ // habilidades
                Integer HabilidadesEmpresa_list = Integer.valueOf(habilidadesEmpresa.get("idhab" +(z+1)));
                atributosEmpresa = atributosEmpresa + 1;
                for (int x= 0;x<habilidadesEstudiante.size();x++) {
                    Integer HabilidadesEstudiante_list=Integer.valueOf( habilidadesEstudiante.get(x).get("id"));
                    if (HabilidadesEmpresa_list ==HabilidadesEstudiante_list) {
                        atributosEstudiante = atributosEstudiante + 1;
                    }
                }
            }
            int size_syt = sytEmpresa.size()/2;
            for (int z=0;z<size_syt;z++){ // Software y Tecnologias
                Integer sytEmpresa_list = Integer.valueOf(sytEmpresa.get("idSyt" +(z+1)));
                Integer nivelSytEmpresa = Integer.valueOf(sytEmpresa.get("nivel"+(z+1)));
                atributosEmpresa = atributosEmpresa + 1;
                for (int x= 0;x<idiomasEstudiante.size();x++) {
                    Integer sytEstudiante_list=Integer.valueOf( sytEstudiante.get(x).get("id"));
                    Integer nivelSytEstudiante = Integer.valueOf(sytEstudiante.get(x).get("nivel"));
                    if ((sytEmpresa_list ==sytEstudiante_list)&&((nivelSytEstudiante==nivelSytEstudiante)||(nivelSytEstudiante>nivelSytEmpresa))) {
                        atributosEstudiante = atributosEstudiante + 1;
                    }
                }
            }
            Integer puntaje = (atributosEstudiante *100) / atributosEmpresa;
            afinidad.put("afinidad", puntaje.toString() + "%");
            list_por_items.add(resulDetalleEstudiantes.get(e));

            list_por_items.add(afinidad);

            listadoOrdenadoDetalle.add(list_por_items);
            result.add(afinidad);
            atributosEmpresa = 0;
            atributosEstudiante=0;
            puntaje=0;
            afinidad = new HashMap<>();
            list_por_items =new ArrayList<>();


        }

        return result;

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
    @PostMapping("consultarSyT")
    public List<SoftwareTecnologias> consultarSyT(HttpServletRequest request, HttpServletResponse response) {
        List<SoftwareTecnologias> ListSyT= softwareTecnologiaRepository.findAllBySytIdGreaterThan(0);
        return ListSyT;
    }
    @PostMapping("consultarHabilidadesBlandas")
    public List<HabilidadesBlandas> consultarHabilidadesBlandas(HttpServletRequest request, HttpServletResponse response) {
        List<HabilidadesBlandas> ListSyT = habilidadesBlandasRepository.findAllByHabIdGreaterThan(0);
        return ListSyT;
    }
    @PostMapping("consultarCiudades")
    public List<Ciudades> consultarCiudades(HttpServletRequest request, HttpServletResponse response) {
        List<Ciudades> ListCiudades= ciudadesRepository.findAllByCiuIdGreaterThan(0);
        return ListCiudades;
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
           resp = estudianteService.postular(idAsignatura,idEmpresa,idEstudiante);
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

    @PostMapping("agregarPerfilPrincipal")
    public Estudiante agregarPerfilPrincipal(HttpServletRequest request, HttpServletResponse response,
                                             @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer usuario 	= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty()) ? Integer.valueOf(params.get(ID_USER).toString()) : null;
        Integer carrera 	= (params.containsKey(CARRERA_ID) && params.get(CARRERA_ID) != null && !params.get(CARRERA_ID).toString().isEmpty()) ? Integer.valueOf(params.get(CARRERA_ID).toString()) : null;
        Integer universidad 	= (params.containsKey(UNIVERSIDAD_ID) && params.get(UNIVERSIDAD_ID) != null && !params.get(UNIVERSIDAD_ID).toString().isEmpty()) ? Integer.valueOf(params.get(UNIVERSIDAD_ID).toString()) : null;
        Integer semestre 	= (params.containsKey(SEMESTRE) && params.get(SEMESTRE) != null && !params.get(SEMESTRE).toString().isEmpty()) ? Integer.valueOf(params.get(SEMESTRE).toString()) : null;
        Integer lugar 	= (params.containsKey(LUGAR) && params.get(LUGAR) != null && !params.get(LUGAR).toString().isEmpty()) ? Integer.valueOf(params.get(LUGAR).toString()) : null;

        // primero consulto perfil estudiante activo actualmente y cambio estatus a inactivo
        Estudiante perfilPrinc =estudianteRepository.consultaPerfilActivo(usuario);
        Integer idEstudianteActivo = perfilPrinc.getId();
        estudianteRepository.updateStatusPerfil(idEstudianteActivo,INACTIVO);
        //cambio estatus de perfil estudiante seleccionado a Activo
        String nombre,apellido,telf,codpais;
        Integer fechaN,pais,ciudad;
        nombre = perfilPrinc.getNombre();
        apellido= perfilPrinc.getApellido();
        fechaN= perfilPrinc.getFechaNac();
        pais=perfilPrinc.getIdpai();
        telf=perfilPrinc.getTelefono();
        codpais=perfilPrinc.getCodigoPais();
        ciudad=perfilPrinc.getIdCiudad();
        Estudiante newPerfil = new Estudiante();
        newPerfil.setNombre(nombre);
        newPerfil.setApellido(apellido);
        newPerfil.setFechaNac(fechaN);
        newPerfil.setIdpai(pais);
        newPerfil.setTelefono(telf);
        newPerfil.setCodigoPais(codpais);
        newPerfil.setIdCiudad(ciudad);
        newPerfil.setIdCar(carrera);
        newPerfil.setIdUni(universidad);
        newPerfil.setSemestre(semestre);
        newPerfil.setLugar(lugar);
        newPerfil.setEstPrincipal(0);
        Estudiante result = estudianteRepository.save(newPerfil);

        return result;
    }

}

