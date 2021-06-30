package com.mosi.mosi.service;

import com.mosi.mosi.bean.*;
import com.mosi.mosi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mosi.mosi.constantes.constante.*;

@Service
public class empresaService {
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    GeneralService generalService;
    @Autowired
    PaisMaestroRepository paisMaestroRepository;
    @Autowired
    PreguntasRepository preguntasRepository;
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

    if (descripcion != null) {
    empresa.setDescripcion(descripcion);
    }
    empresa.setRubro(rubroRepository.findById(rubro).get());
    if (ubicacion != null) {
        empresa.setUbicacion(ubicacion);
    }
    empresa.setPais(paisService.findPaisbyId(pais));
    if (sitioW != null) {
        empresa.setSitioWeb(sitioW);
    }
    empresa.setCorreo(correo);
    empresa.setNombre(nombre);
    if (razonSocial != null) {
        empresa.setRazonsocial(razonSocial);
    }
    if (telefono != null) {
        empresa.setTelefono(codigoPais + " " + telefono);
    }
    if (mision != null) {
        empresa.setMision(mision);
    }
    if (vision != null) {
        empresa.setVision(vision);
    }
    empresa.setUsers(userService.findUsersbyId(usuId));

    Empresa result = empresaRepository.save(empresa);


    return empresa;
}



    public Empresa consultaPerfilEmpresa(Integer idUsu) {
        Empresa empresa = empresaRepository.findByUsers(userService.findUsersbyId(idUsu));
        return empresa;
    }


    public DetalleEstudiante guardarEstudiante(List<?> pais, List<?> deporte, Integer empId, Integer idAsi, List<?> idioma,
                                               Integer universidad, Integer carrera, Integer semestre, String descripcion, List<?> softYTecn, List<?> hablidadesB, List<?> preguntas){
        DetalleEstudiante detEstudiante = new DetalleEstudiante();
        DetalleEstudiante estudiante = new DetalleEstudiante();
        PaisMaestro paises = new PaisMaestro();

        Asignatura asignatura =asignaturaRepository.findByAsiId(idAsi);

        try {
            if (descripcion!=null) {
                estudiante.setDetDescripcion(descripcion);
            }
            estudiante.setAsignatura(asignatura);
            estudiante.setEmpresa(empresaRepository.findById(empId).get());

            if (semestre != null) {
                estudiante.setDetSem(semestre);
            }
            estudiante.setCarrera(carreraService.findCarreraById(carrera));
            if (universidad!=null){
                estudiante.setUniversidad(universidadService.findUniversidadById(universidad));
            }

            estudiante = detalleEstudianteRepository.save(estudiante);
            for(int i=0;i<pais.size();i++){
            paises.setPaises(paisesRepository.findById(Integer.valueOf(pais.get(i).toString())).get());
            paises.setDetalleEstudiante(estudiante);
            paises =  paisMaestroRepository.save(paises);
            paises = new PaisMaestro();
            }
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
        HashMap<String, Object> pyr = new HashMap<>();
        List<Postulaciones> listPostulacions = postulacionesRepository.getByAsignatura(asignaturaRepository.findByAsiId(idAsig));
        Asignatura asig = asignaturaRepository.getById(idAsig);
        List<HashMap<String,Object>>list = new ArrayList<>();
        List<HashMap<String,Object>> resp = new ArrayList<>();
        for (Postulaciones p:listPostulacions) {
            est.put("IdEstudiante",p.getEstudiante().getId());
            est.put("nombreYapellido",p.getEstudiante().getNombre()+ " "+p.getEstudiante().getApellido());
            est.put("carrera",p.getEstudiante().getCarrera().getNombreCarrera());
            est.put("universidad",p.getEstudiante().getUniversidad().getNombreUni());
            est.put("pais",p.getEstudiante().getPais().getNombrePais());
            est.put("ciudad",p.getEstudiante().getCiudad().getCiuNombre());
            est.put("afinidad",p.getCompatibilidad().toString());
            est.put("Asignatura",asig);
            List<Respuestas> respu = respuestaRepository.findByEstudianteAndAsignatura(p.getEstudiante(),p.getAsignatura());
            for (Respuestas r: respu){
            res.put("Pregunta",r.getPregunta().getDecripcion());
            res.put("Respuesta",  r.getResRespuestas());
            resp.add(res);
            res = new HashMap<>();
            }

            pyr.put("PreguntasYRespuestas",resp);
            List<List<HashMap<String, Object>>> compatibilidad =estudianteService.consultarAfinidad(p.getEstudiante().getId(),p.getAsignatura().getAsiId());
            list.add(compatibilidad.get(0).get(2));
            list.add(est);
            list.add(pyr);
            est = new HashMap<>();
            res = new HashMap<>();

        }

        return list;
    }

    public Postulaciones cambiarEstatusPostulacion(Integer idEstudiante, Integer idAsignatura, Integer estatus,Integer idPos) {
        Postulaciones postulaciones = new Postulaciones();
        if (idPos!=null){
            postulaciones = postulacionesRepository.findById(idPos).get();
        }else {
            Asignatura asignatura = asignaturaRepository.findByAsiId(idAsignatura);
            Estudiante estudiante = estudianteRepository.findById(idEstudiante).get();
            postulaciones = postulacionesRepository.findByEstudianteAndAsignatura(estudiante, asignatura);
        }
            postulaciones.setPosEstatus(estatus);
            postulacionesRepository.save(postulaciones);

        return postulaciones;
    }

    public List<Postulaciones> verEstudiantesSeleccionados(Integer idAsignatura) {

    Asignatura asignatura= asignaturaRepository.findByAsiId(idAsignatura);
    List<Postulaciones> listSelec = postulacionesRepository.findByAsignaturaAndPosEstatus(asignatura,ACEPTADO);
        return listSelec;
    }

    public HashMap<String, Object> buscarPreguntasYRespuestas(Estudiante estudiante, Postulaciones postulaciones) {
        Preguntas preguntas = new Preguntas();
        Respuestas resp = new Respuestas();
        HashMap<String, Object> pregYresp = new HashMap<>();
        List<Respuestas> respuestas = new ArrayList<>();

        respuestas= respuestaRepository.findByEstudianteAndAsignatura(estudiante,postulaciones.getAsignatura());

        for (Respuestas r:respuestas
             ) {
            preguntas = preguntasRepository.findById(r.getPregunta().getId()).get();
            pregYresp.put(preguntas.getDecripcion().toString(),r.getResRespuestas());
        }
        return pregYresp;
    }

    public List<HashMap<String,Object>> sugerirEstudiante(Integer idAsignatura) {
    List<Integer> idsPaises = new ArrayList<>();
    Asignatura asignatura = asignaturaRepository.findByAsiId(idAsignatura);
        List<DetalleEstudiante> detalle = detalleEstudianteRepository.findByAsignatura(asignatura);
        for (DetalleEstudiante det: detalle
             ) {
        List<PaisMaestro> paises = paisMaestroRepository.findByDetalleEstudiante(det);
        for (int i=0;i<paises.size();i++){
            idsPaises.add(paises.get(i).getPaises().getId());
        }

        }
        List<Integer> carrera = new ArrayList<>();
        for (DetalleEstudiante det:detalle
             ) {
            carrera.add(det.getCarrera().getId());
        }

        List<Estudiante> estudiantes = estudianteService.consultaEstudiantesSugeridos(carrera,idsPaises);
        List<HashMap<String,Object>> listEstudiantes = this.compatibilidadEstudiantes(detalle,estudiantes);

        return listEstudiantes;
    }

    public List<HashMap<String,Object>> compatibilidadEstudiantes(List<DetalleEstudiante>detalleEstudianteList,List<Estudiante>estudiantesList ){
    Integer semDet = 0;
    Integer semEst = 0;
    Integer univDet = 0;
    Integer univEst = 0;
    Integer lugarEst = 0;
    Integer lugarDet = 0;
    Integer atributosEstudiante = 2;
    Integer atributoEmpresa = 2;
    HashMap<String, Object> datosCompatibles = new HashMap<>();
        HashMap<String, Object> afinidad = new HashMap<>();

        List<HashMap<String,Object>> ListEstudiantesCompatibles = new ArrayList<>();
    List<String> idiomasComp = new ArrayList<>();
        List<String> deporteComp = new ArrayList<>();
        List<String> habilidadesComp = new ArrayList<>();
        List<String> syTComp = new ArrayList<>();



        for (DetalleEstudiante det: detalleEstudianteList
             ) {
            for (Estudiante est: estudiantesList
                 ) {
                if (det.getCarrera().getId() == est.getCarrera().getId()) {
                    semDet = (det.getDetSem() != null) ? det.getDetSem() : 0;
                    semEst = (est.getSemestre() != null) ? est.getSemestre() : 0;
                    univDet = (det.getUniversidad() != null) ? det.getUniversidad().getId() : 0;
                    univEst = (est.getUniversidad() != null) ? est.getUniversidad().getId() : 0;
                    if (det.getAsignatura().getAsiLugar() != null) {
                        lugarDet = det.getAsignatura().getAsiLugar();
                        atributoEmpresa = atributoEmpresa + 1;
                    }
                    lugarEst = (est.getLugar() != null) ? est.getLugar() : 0;
                    atributoEmpresa = (semDet != 0) ? atributoEmpresa + 1 : atributoEmpresa;

                    if ((semEst == semDet) || (semEst > semDet)) {
                        atributosEstudiante = atributosEstudiante + 1;
                        datosCompatibles.put("Nombre y apellido: ", est.getNombre() + " " + est.getApellido());
                        datosCompatibles.put("Carrera: ", est.getCarrera().getNombreCarrera());
                        datosCompatibles.put("Pais: ", est.getPais().getNombrePais());
                        if (det.getUniversidad() != null && est.getUniversidad() != null) {
                            atributosEstudiante = atributosEstudiante + 1;
                            datosCompatibles.put("Universidad: ", est.getCarrera().getNombreCarrera());
                        }
                        datosCompatibles.put("Semestre: ", semEst);
                        if ((lugarDet == lugarEst) || (lugarEst==3)) {
                            atributosEstudiante = atributosEstudiante + 1;
                            if ((lugarEst == PRESENCIAL)) {
                                datosCompatibles.put("lugar", "Presencial");
                            } else if (lugarEst == REMOTO) {
                                datosCompatibles.put("lugar", "Remoto");
                            } else if (lugarEst == AMBOS) {
                                datosCompatibles.put("lugar", "Remoto o Presencial");
                            }
                        }
                        /** Idiomas*/
                        List<IdiomaMaestro> idiomasEmpresa = idiomaMaestroRepository.findByDetalleEstudiante(det);
                        List<IdiomaMaestro> idiomasEstudiante = idiomaMaestroRepository.findByEstudiante(est);
                        if (idiomasEmpresa != null && idiomasEstudiante != null) {
                            for (IdiomaMaestro idiEmp : idiomasEmpresa
                            ) {
                                atributoEmpresa = atributoEmpresa + 1;
                                for (IdiomaMaestro idiEst : idiomasEstudiante
                                ) {
                                    if ((idiEmp.getIdioma().getId() == idiEst.getIdioma().getId()) && ((idiEmp.getNivel() == idiEst.getNivel()) || (idiEst.getNivel() > idiEmp.getNivel()))) {
                                        atributosEstudiante = atributosEstudiante + 1;
                                        String nivel = "";
                                        switch (idiEst.getNivel().intValue()) {
                                            case 1: //Basico
                                                nivel = "Basico";
                                                break;
                                            case 2: //Intermedio
                                                nivel = "Intermedio";
                                                break;
                                            case 3: // Avanzado
                                                nivel = "Avanzado";
                                                break;
                                        }
                                        idiomasComp.add(idiEst.getIdioma().getNombreIdioma() + "-" + nivel);
                                    }
                                }
                            }
                            datosCompatibles.put("Idiomas:", idiomasComp);
                            idiomasComp = new ArrayList<>();
                        }
                        /** Deporte*/
                        List<DeporteMaestro> listDepEmp = deporteMaestroRepository.findByDetalleEstudiante(det);
                        List<DeporteMaestro> listDepEst = deporteMaestroRepository.findByEstudiante(est);
                        if (listDepEmp != null && listDepEst != null) {
                            for (DeporteMaestro depEmp : listDepEmp
                            ) {
                                atributoEmpresa = atributoEmpresa + 1;
                                for (DeporteMaestro depEst : listDepEst
                                ) {
                                    if (depEmp.getDeporte().getId() == depEst.getDeporte().getId()) {
                                        atributosEstudiante = atributosEstudiante + 1;
                                        deporteComp.add(depEst.getDeporte().getNombreDeporte());
                                    }
                                }

                            }
                        }
                        datosCompatibles.put("deportes: ", deporteComp);
                        deporteComp = new ArrayList<>();
                        /** Habilidades*/
                        List<HabilidadesBlandasMaestro> listHabEmp = habilidadesBlandasMaestroRepository.findByDetalleEstudiante(det);
                        List<HabilidadesBlandasMaestro> listHabEst = habilidadesBlandasMaestroRepository.findByEstudiante(est);
                        if (listHabEmp != null && listHabEst != null) {
                            for (HabilidadesBlandasMaestro habEmp : listHabEmp
                            ) {
                                atributoEmpresa = atributoEmpresa + 1;
                                for (HabilidadesBlandasMaestro habEst : listHabEst
                                ) {
                                    if (habEmp.getHabilidadesBlandas().getHabId() == habEst.getHabilidadesBlandas().getHabId()) {
                                        atributosEstudiante = atributosEstudiante + 1;
                                        habilidadesComp.add(habEst.getHabilidadesBlandas().getHabNombre());
                                    }
                                }
                            }
                        }
                        datosCompatibles.put("habilidades: ", habilidadesComp);
                        habilidadesComp = new ArrayList<>();
                        /** Software y Tecnologias*/
                        List<SoftwareTecnologiasMaestro> sytEmpresa = softwareTecnologiaMaestroRepository.findByDetalleEstudiante(det);
                        List<SoftwareTecnologiasMaestro> sytEstudiante = softwareTecnologiaMaestroRepository.findByEstudiante(est);
                        if (sytEmpresa != null && sytEstudiante != null) {
                            for (SoftwareTecnologiasMaestro sytEmp : sytEmpresa
                            ) {
                                atributoEmpresa = atributoEmpresa + 1;
                                for (SoftwareTecnologiasMaestro sytEst : sytEstudiante
                                ) {
                                    if ((sytEmp.getSoftwareTecnologias().getSytId() == sytEst.getSoftwareTecnologias().getSytId()) &&
                                            ((sytEmp.getNivel() == sytEst.getNivel()) || (sytEmp.getNivel() > sytEst.getNivel()))) {
                                        atributosEstudiante = atributosEstudiante + 1;
                                        String nivel = "";
                                        switch (sytEst.getNivel().intValue()) {
                                            case 1: //Basico
                                                nivel = "Basico";
                                                break;
                                            case 2: //Intermedio
                                                nivel = "Intermedio";
                                                break;
                                            case 3: // Avanzado
                                                nivel = "Avanzado";
                                                break;
                                        }
                                        syTComp.add(sytEst.getSoftwareTecnologias().getSytNombre() + " - " + nivel);
                                    }
                                }
                            }

                            datosCompatibles.put("SoftWare y Tecnologias:", syTComp);
                            syTComp = new ArrayList<>();
                        }
                        Integer puntaje = (atributosEstudiante * 100) / atributoEmpresa;
                        afinidad.put("afinidad", puntaje.toString());
                        datosCompatibles.put("afinidad", afinidad);
                        ListEstudiantesCompatibles.add(datosCompatibles);
                        datosCompatibles = new HashMap<>();
                        atributoEmpresa = 2;
                        atributosEstudiante = 2;
                        afinidad = new HashMap<>();
                    }
                }
            }

        }
    return ListEstudiantesCompatibles;
    }

    public String rechazarEstudiante(Integer postulacion) throws IOException, MessagingException {
    Postulaciones estudiante = new Postulaciones();

        Postulaciones rechazar= this.cambiarEstatusPostulacion(null,null,RECHAZADO,postulacion);
        String mensaje = "";
        if(rechazar!=null){
            String email = rechazar.getEstudiante().getCorreo();
            String msj = DETALLE_EMAIL_RECHAZA_ESTUDIANTE + estudiante.getEmpresa().getNombre();
            generalService.enviarEmail(email,ASUNTO,msj);
            mensaje ="Se ha rechazado el estudiante exitosamente!";
        }
        return mensaje;
    }
}
