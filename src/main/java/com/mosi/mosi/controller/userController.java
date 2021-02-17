package com.mosi.mosi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosi.mosi.bean.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mosi.mosi.constantes.constante.*;


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
        Integer lugar = (params.containsKey(LUGAR) && params.get(LUGAR) != null && !params.get(LUGAR).toString().isEmpty())
                ? Integer.valueOf(params.get(LUGAR).toString()) : null;

        estudiante = estudianteService.guardarEstudiante(nombresEstudiante,apellidosEstudiante,fechaNac,pais,ciudad,telefono,codigoPais,correo,deporte,
                idioma,universidad,carrera, usuario,semestre,pasatiempo,descripcion,softYTecn,hablidadesB,lugar);

        return estudiante;

    }
    /*
    * Metodo Para Obtener Perfil del Estudiante
    * */
    @PostMapping("/consultarEstudiante")
    public  List<Object> consultarEstudiante(
            @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {

        IdiomaMaestro idi =new IdiomaMaestro();
        CarreraUniversitariaMaestro carr =new CarreraUniversitariaMaestro();
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer usuario 	= (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty()) ? Integer.valueOf(params.get(ID_USER).toString()) : null;

        List<Object> perfilEstudianteDetallado = estudianteService.consulta(usuario, 0) ;

        return perfilEstudianteDetallado;

    }


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
    @PostMapping("buscar_Practica_desafio")
    public List<List<HashMap<String, Object>>> buscarPracticaDesafio(
            @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer usuario = (params.containsKey(ID_USER) && params.get(ID_USER) != null && !params.get(ID_USER).toString().isEmpty()) ?
                Integer.valueOf(params.get(ID_USER).toString()) : null;
        Integer tipo = (params.containsKey(TIPO) && params.get(TIPO) != null && !params.get(TIPO).toString().isEmpty()) ?
                Integer.valueOf(params.get(TIPO).toString()) : null;
        List<List<HashMap<String, Object>>> result = new ArrayList<>();
        result = estudianteService.buscarCompatibilidad(usuario,0,tipo);
        return result;

    }


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
        List<Ciudades> ListCiudades= ciudadesRepository.findByIdGreaterThan(0);
        return ListCiudades;
    }
    @PostMapping("postularEstudiante")
    public String postularEstudiante(HttpServletRequest request, HttpServletResponse response,
                                               @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String resp = "";
        Integer idAsignatura = (params.containsKey(ID_ASIGNATURA) && params.get(ID_ASIGNATURA) != null) ? Integer.valueOf(params.get(ID_ASIGNATURA).toString()): null;
        Integer idEmpresa = (params.containsKey(ID_EMPRESA) && params.get(ID_EMPRESA) != null) ? Integer.valueOf(params.get(ID_EMPRESA).toString()) : null;
        Integer idEstudiante = (params.containsKey(ID_ESTUDIANTE) && params.get(ID_ESTUDIANTE) != null) ? Integer.valueOf(params.get(ID_ESTUDIANTE).toString()) : null;
        List<List<HashMap<String, Object>>>result = new ArrayList<>();

        Integer afinidad=0;
        //result = estudianteService.buscarCompatibilidad(0,idEstudiante,idAsignatura);
        Object asign = new Object();
       /* for (int i=0; i<result.size();i++){
            asign= result.get(i).get(0).get("asignatura");
            Integer id = Integer.valueOf(asign[0][0].toString());
            if  (idAsignatura ==id){
                afinidad = (Integer.valueOf(result.get(i).get(1).get("afinidad").toString()));
            }
        }*/
        if (idAsignatura!=null && idEmpresa!=null && idEstudiante!=null){
           resp = estudianteService.postular(idAsignatura,idEmpresa,idEstudiante);
        }
        return resp;
    }
    @PostMapping("detalleAsignatura")
    public Asignatura detalleAsignatura(HttpServletRequest request, HttpServletResponse response,
                                     @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idAsignatura 	= (params.containsKey(ID_ASIGNATURA) && params.get(ID_ASIGNATURA) != null) ? Integer.valueOf(params.get(ID_ASIGNATURA).toString()): null;
        Asignatura asignatura = asignaturaRepository.findByAsiId(idAsignatura);


        return asignatura;
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
        String nombre,apellido,telf,codpais,descripcion,correo;
        Integer fechaN,pais,ciudad;
        nombre = perfilPrinc.getNombre();
        apellido= perfilPrinc.getApellido();
        fechaN= perfilPrinc.getFechaNac();
        pais=perfilPrinc.getPais().getId();
        telf=perfilPrinc.getTelefono();
        codpais=perfilPrinc.getCodigoPais();
        ciudad=perfilPrinc.getCiudad().getId();
        descripcion= perfilPrinc.getDescripcion();
        correo=perfilPrinc.getCorreo();
        Integer idEst = perfilPrinc.getId();

        List<?> list_Idi = idiomaMaestroRepository.consultaIdiomaEstudiante(idEst);
        List<?> list_Dep =deporteMaestroRepository.consultaEstudiante(idEst);
        List<?> list_Hab = habilidadesBlandasMaestroRepository.consultarHablidadesPorEstudiante(idEst);
        List<?> list_Syt = softwareTecnologiaMaestroRepository.consultarSotfwareyTecnEstudiante(idEst);
        List<?> list_Pasatiempo = pasatiempoMaestroRepository.consultaPasatiempoMaestroEstudiante(idEst);

        Estudiante newPerfil = new Estudiante();
        newPerfil.setNombre(nombre);
        newPerfil.setApellido(apellido);
        newPerfil.setFechaNac(fechaN);
        newPerfil.setPais(paisService.findPaisbyId(pais));
        newPerfil.setTelefono(telf);
        newPerfil.setCodigoPais(codpais);
        newPerfil.setCiudad(ciudadService.findCiudadById(ciudad));
        newPerfil.setCarrera(carreraService.findCarreraById(carrera));
        newPerfil.setUniversidad(universidadService.findUniversidadById(universidad));
        newPerfil.setSemestre(semestre);
        newPerfil.setLugar(lugar);
        newPerfil.setDescripcion(descripcion);
        newPerfil.setCorreo(correo);
        newPerfil.setEstPrincipal(0);
        newPerfil.setUsuario(userService.findUsersbyId(usuario));
        Estudiante result = estudianteRepository.save(newPerfil);
       // Estudiante result = new Estudiante();
        Boolean caracteristica = estudianteService.guardar_Dep_idi_hab_syt(list_Dep,list_Idi,list_Hab,list_Syt,list_Pasatiempo,result.getId(),ESTUDIANTE);
        //Estudiante result = new Estudiante();


        return result;
    }


    @PostMapping("agregarPasatiempos")
    public Pasatiempo agregarPasatiempos(HttpServletRequest request, HttpServletResponse response,
                                             @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String nombrePas 	= (params.containsKey(NOMBRE_PASATIEMPO) && params.get(NOMBRE_PASATIEMPO) != null) ? params.get(NOMBRE_PASATIEMPO).toString() : null;

        Pasatiempo pasatiempo = new Pasatiempo();
        pasatiempo.setNombrePasatiempo(nombrePas);
        pasatiempo = pasatiempoRepository.save(pasatiempo);

       return pasatiempo;
    }
    @PostMapping("agregarSYT")
    public SoftwareTecnologias agregarSYT(HttpServletRequest request, HttpServletResponse response,
                                         @ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        String nombreSYT 	= (params.containsKey(NOMBRE_SYT) && params.get(NOMBRE_SYT) != null) ? params.get(NOMBRE_SYT).toString() : null;

        SoftwareTecnologias syt = new SoftwareTecnologias();
        syt.setSytNombre(nombreSYT);
        syt = softwareTecnologiaRepository.save(syt);

        return syt;
    }

}

