package com.mosi.mosi.service;

import com.mosi.mosi.bean.*;
import com.mosi.mosi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mosi.mosi.constantes.constante.ACEPTADO;
import static com.mosi.mosi.constantes.constante.EMPRESA;

@Service
public class empresaService {
    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    universidadService universidadService;
    @Autowired
    carreraService carreraService;
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    PaisesRepository paisesRepository;
    @Autowired
    RubroRepository rubroRepository;
    @Autowired
    DetalleEstudianteRepository detalleEstudianteRepository;
    @Autowired
    EstudianteService estudianteService;
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    paisService paisService;
    @Autowired
    UserService userService;
    @Autowired
    IdiomaMaestroRepository idiomaMaestroRepository;
    @Autowired
    IdiomaRepository idiomaRepository;
    @Autowired
    DeporteMaestroRepository deporteMaestroRepository;
    @Autowired
    DeporteRepository deporteRepository;
    @Autowired
    HabilidadesBlandasMaestroRepository habilidadesBlandasMaestroRepository;
    @Autowired
    HabilidadesBlandasRepository habilidadesBlandasRepository;
    @Autowired
    SoftwareTecnologiaMaestroRepository softwareTecnologiaMaestroRepository;
    @Autowired
    SoftwareTecnologiaRepository softwareTecnologiaRepository;
    @Autowired
    PostulacionesRepository postulacionesRepository;

public Empresa guardarPerfilEmpresa (String descripcion,Integer rubro,String ubicacion,Integer pais,String sitioW, String codigoPais,String correo,
                                     String nombre,String razonSocial,String telefono,String mision,String vision,Integer usuId){
    Empresa empresa = new Empresa();

    empresa.setDescripcion(descripcion);
    empresa.setRubro(rubroRepository.findById(rubro).get());
    empresa.setUbicacion(ubicacion);
    empresa.setPais(paisService.findPaisbyId(pais));
    empresa.setSitioWeb(sitioW);
    empresa.setCorreo(correo);
    empresa.setNombre(nombre);
    empresa.setRazonsocial(razonSocial);
    empresa.setTelefono(codigoPais + " " + telefono);
    empresa.setMision(mision);
    empresa.setVision(vision);
    empresa.setUsers(userService.findUsersbyId(usuId));

    Empresa result = empresaRepository.save(empresa);


    return empresa;
}



    public Empresa consultaPerfilEmpresa(Integer idUsu) {
        Empresa empresa = empresaRepository.findByUsers(userService.findUsersbyId(idUsu));
        return empresa;
    }


    public DetalleEstudiante guardarEstudiante(Integer pais, List<?> deporte, Integer empId, Integer asignatura, List<?> idioma,
                                                      Integer universidad, Integer carrera, Integer semestre, String descripcion, List<?> softYTecn, List<?> hablidadesB){
        DetalleEstudiante detEstudiante = new DetalleEstudiante();
        DetalleEstudiante estudiante = new DetalleEstudiante();
        try {
            if (descripcion!=null) {
                estudiante.setDetDescripcion(descripcion);
            }
            estudiante.setAsignatura(asignaturaRepository.findByAsiId(asignatura));
            estudiante.setEmpresa(empresaRepository.findById(empId).get());
            estudiante.setPais(paisesRepository.findById(pais).get());
            if (semestre != null) {
                estudiante.setDetSem(semestre);
            }
            estudiante.setCarrera(carreraService.findCarreraById(carrera));
            if (universidad!=null){
                estudiante.setUniversidad(universidadService.findUniversidadById(universidad));
            }

            estudiante = detalleEstudianteRepository.save(estudiante);

            Boolean saveCaracteristicas = estudianteService.guardarcaracteristicas(idioma,deporte,null, hablidadesB, softYTecn, null, estudiante, EMPRESA);
            if (saveCaracteristicas==true){
                String si = "si";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



        return estudiante;

    }
    public Empresa buscarEmpresaporId(Integer idEmp){
    Empresa empresa = new Empresa();

    empresa =  empresaRepository.findById(idEmp).get();

    return empresa;
    }

    public List<HashMap<String,Object>> verDetallePractDesaf(Integer idAsig){
        Asignatura asignatura;
        List<Idioma> idionaL = new ArrayList<>();
        List<Deporte> deporteL = new ArrayList<>();
        List<HabilidadesBlandas> habilidadesL = new ArrayList<>();
        List<SoftwareTecnologias> sytL = new ArrayList<>();
        HashMap<String,Object> listCaract = new HashMap<>();
        List<Object> caracteristicas = new ArrayList<>();
        List<HashMap<String,Object>> result = new ArrayList<>();
        asignatura= asignaturaRepository.findByAsiId(idAsig);
        List<DetalleEstudiante> estudiante = detalleEstudianteRepository.findByAsignaturaAndEmpresa(asignatura,asignatura.getEmpresa());
        for (int i=0; i<estudiante.size();i++){
        caracteristicas = estudianteService.consultaCaracteristicas(null,estudiante.get(i),EMPRESA);
            listCaract.put("caracteristicas",estudiante.get(i));
            listCaract.put("deporte",caracteristicas.get(1));
            listCaract.put("idioma",caracteristicas.get(2));
            listCaract.put("habilidades",caracteristicas.get(3));
            listCaract.put("syt",caracteristicas.get(5));
        result.add(listCaract);
            listCaract = new HashMap<>();
        }
        return result;
    }
    public List<HashMap<String,Object>>verPostulante(Integer idAsig) {
        HashMap<String, Object> est = new HashMap<>();
        HashMap<String, Object> res = new HashMap<>();
        List<Postulaciones> listPostulacions = postulacionesRepository.getByAsignatura(asignaturaRepository.findByAsiId(idAsig));
        List<HashMap<String,Object>>list = new ArrayList<>();
        for (Postulaciones p:listPostulacions) {
            est.put("nombreYapellido",p.getEstudiante().getNombre()+ " "+p.getEstudiante().getApellido());
            est.put("carrera",p.getEstudiante().getCarrera().getNombreCarrera());
            est.put("universidad",p.getEstudiante().getUniversidad().getNombreUni());
            est.put("pais",p.getEstudiante().getPais().getNombrePais());
            est.put("ciudad",p.getEstudiante().getCiudad().getCiuNombre());
            est.put("afinidad",p.getCompatibilidad().toString());
            List<Respuestas> respu = respuestaRepository.findByEstudianteAndAsignatura(p.getEstudiante(),p.getAsignatura());
            for (Respuestas r: respu){
            res.put("Pregunta",r.getPregunta().getDecripcion());
            res.put("Respuesta",  r.getResRespuestas());
            }
            List<List<HashMap<String, Object>>> compatibilidad =estudianteService.consultarAfinidad(p.getEstudiante().getId(),p.getAsignatura().getAsiId());
            list.add(compatibilidad.get(0).get(2));
            list.add(est);
            list.add(res);
            est = new HashMap<>();
            res = new HashMap<>();

        }

        return list;
    }

    public int seleccionarEstudiante(Integer idEstudiante, Integer idAsignatura) {

        int seleccionar =postulacionesRepository.seleccionarEstudiante(idEstudiante,idAsignatura,ACEPTADO);
        return seleccionar;
    }

    public List<Postulaciones> verEstudiantesSeleccionados(Integer idAsignatura) {

    Asignatura asignatura= asignaturaRepository.findByAsiId(idAsignatura);
    List<Postulaciones> listSelec = postulacionesRepository.findByAsignaturaAndPosEstatus(asignatura,ACEPTADO);
        return listSelec;
    }
}
