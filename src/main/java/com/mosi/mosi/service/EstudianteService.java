package com.mosi.mosi.service;

import com.mosi.mosi.bean.*;
import com.mosi.mosi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.mosi.mosi.constantes.constante.ENVIADO;

@Service
public class EstudianteService {
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
    PasatiempoMaestroRepository pasatiempoMaestroRepository;
    @Autowired
    PostulacionesRepository postulacionesRepository;

public Estudiante guardarEstudiante(String nombresEstudiante, String apellidosEstudiante,
                                    Integer fechaNac, Integer pais, List<?>deporte,
                                    List<?> idioma, Integer universidad, Integer especialidad, Integer carrera, Integer usuario,Integer semestre,
                                    List<?> pasatiempo,String pasion){
    Estudiante estudiante = new Estudiante();
    DeporteMaestro dep =new DeporteMaestro();
    IdiomaMaestro idi =new IdiomaMaestro();
    PasatiempoMaestro hom =new PasatiempoMaestro();
    PasionMaestro pam = new PasionMaestro();


    if ((!nombresEstudiante.isEmpty()) && (!apellidosEstudiante.isEmpty()) && (fechaNac != null)
            &&(pais != null) &&(deporte != null) && (idioma != null) && (universidad!= null) &&
            (especialidad!= null) && (carrera != null) && (usuario!=null) &&(semestre!=null) && (pasatiempo!=null) && (pasion!=null)){
        estudiante.setNombre(nombresEstudiante);
        estudiante.setApellido(apellidosEstudiante);
        estudiante.setFechaNac(fechaNac);
        estudiante.setIdpai(pais);
        estudiante.setUsuid(usuario);
        estudiante.setIdEsp(especialidad);
        estudiante.setIdCar(carrera);
        estudiante.setIdUni(universidad);
        estudiante.setEstPrincipal(1);
       // estudiante.setPasId(pasion);
        estudiante.setSemestre(semestre);
        estudiante = estudianteRepository.save(estudiante);
        int est = estudiante.getId();
        if (estudiante.getId()!=null){
            for (int i=0;i < deporte.size();i++){ //guardando deporte en tabla maestra
                dep.setDepIdDeporte(Integer.valueOf(deporte.get(i).toString()));
                dep.setEstIdDep(est);
                deporteMaestroRepository.save(dep);
                dep = new DeporteMaestro();
            }

            for (int i=0;i < idioma.size();i++){ //guardando idioma en tabla maestra
                idi.setIdiIdIdioma(Integer.valueOf(idioma.get(i).toString()));
                idi.setEstIDIdioma(est);
                idiomaMaestroRepository.save(idi);
                idi= new IdiomaMaestro();
            }
            for (int i=0;i < pasatiempo.size();i++){ //guardando Pasatiempo en tabla maestra
                hom.setHobId(Integer.valueOf(pasatiempo.get(i).toString()));
                hom.setEstId(est);
                pasatiempoMaestroRepository.save(hom);
                idi= new IdiomaMaestro();
            }
            pam.setDescripcion(pasion);
            pam.setEstId(est);
            pasionesMaestroRepository.save(pam);
            pam= new PasionMaestro();
        }

    }
    return estudiante;
}

public List<List<HashMap<String, String>>> consultaEstudiante(Integer usuario){
    List<List<HashMap<String, String>>> result = new ArrayList<>();
    result= this.consulta(usuario);
    return result;
}

    public  List<List<HashMap<String, String>>> consulta(Integer usuario){
        List<Object[]> perfil = estudianteRepository.consultaEstudiante(usuario);

        List<HashMap<String,String>> perfilEstudiante= this.construirEstudiante(perfil);
        Integer idEst = Integer.valueOf(perfilEstudiante.get(0).get("idEstudiante"));
        List<Integer> depiD = deporteMaestroRepository.consultaEstudiante(idEst);
        List<Object[]> depo = deporteRepository.consultaDeporteEstudiante(depiD);
        List<HashMap<String, String>> resulDepo= this.listado(depo);

        /*idioma*/
        List<Integer> idiId = idiomaMaestroRepository.consultaIdiomaEstudiante(idEst);
        List<Object[]> idio = idiomaRepository.consultaIdiomaEstudiante(idiId);
        List<HashMap<String, String>> resulIdi= this.listado(idio);


        /*pasatiempo*/
        List<Integer> pasId = pasatiempoMaestroRepository.consultaPasatiempoMaestroEstudiante(idEst);
        List<Object[]> pasa = pasatiempoRepository.consultaPasatiempoEstudiante(pasId);
        List<HashMap<String, String>> resultPas= this.listado(pasa);

        /*Pasion (descripcion)*/
        List<Object[]> descripcion = pasionesMaestroRepository.consultarDescripcion(idEst);
        HashMap<String,String> pasList= new HashMap<String, String>();
        pasList.put("id",descripcion.get(0)[0].toString());
        pasList.put("descripcion",descripcion.get(0)[3].toString());
        List<HashMap<String, String>> resultpam= new ArrayList<>();
        resultpam.add(pasList);

        List<List<HashMap<String, String>>> result= new ArrayList<>();
        result.add(perfilEstudiante);
        result.add(resulDepo);
        result.add(resulIdi);
        result.add(resultPas);
        result.add(resultpam);

        return result;

    }

    public static List<HashMap<String,String>> listado(List<Object[]> resul) {
        HashMap<String,String> depoList= new HashMap<String, String>();
        List<HashMap<String, String>> resulDepo= new ArrayList<>();
        for (int i =0;i<resul.size();i++){
            depoList.put("id",resul.get(i)[0].toString());
            depoList.put("Nombre",resul.get(i)[1].toString());
            resulDepo.add(depoList);
            depoList = new HashMap<String, String>();
        }

        return resulDepo;
    }

    public static List<HashMap<String,String>>construirEstudiante(List<Object[]> perfil) {
        List<HashMap<String,String>> perfilEstudiante = new ArrayList<>(); ;
        HashMap<String,String> listPerfil = new HashMap<String, String>();
        listPerfil.put("nombre", perfil.get(0)[0].toString());
        listPerfil.put("apellido", perfil.get(0)[1].toString());
        listPerfil.put("FechaNac", perfil.get(0)[2].toString());
        listPerfil.put("pais", perfil.get(0)[3].toString());
        listPerfil.put("nombreUsuario", perfil.get(0)[4].toString());
        listPerfil.put("idEstudiante", perfil.get(0)[5].toString());
        listPerfil.put("idPais",perfil.get(0)[6].toString());
        listPerfil.put("Semestre",perfil.get(0)[7].toString());
        listPerfil.put("Especialidad",perfil.get(0)[8].toString());
        listPerfil.put("Carrera",perfil.get(0)[9].toString());
       // listPerfil.put("Pasion",perfil.get(0)[10].toString());
        listPerfil.put("Universidad",perfil.get(0)[10].toString());


        perfilEstudiante.add(listPerfil);

        return perfilEstudiante;
    }


    public static HashMap<String,String> doConstruirEstudiante(List<Object[]> perfil) {
        HashMap<String, String> listPerfil = new HashMap<String, String>();

            listPerfil.put("nombre", perfil.get(0)[0].toString());
            listPerfil.put("apellido", perfil.get(0)[1].toString());
            listPerfil.put("FechaNac", perfil.get(0)[2].toString());
            listPerfil.put("pais", perfil.get(0)[3].toString());
            listPerfil.put("nombreUsuario", perfil.get(0)[4].toString());
            listPerfil.put("est_id", perfil.get(0)[5].toString());
            listPerfil.put("idPais", perfil.get(0)[6].toString());
            listPerfil.put("Semestre", perfil.get(0)[7].toString());
            // perfilEstudiante.add(listPerfil);
            if (perfil.get(0).length>8) {
            listPerfil.put("especialidad", perfil.get(0)[8].toString());
            listPerfil.put("carrera", perfil.get(0)[9].toString());
            listPerfil.put("universidad", perfil.get(0)[10].toString());
        }
        return listPerfil;
    }

    public static Map<String, Object> commonErrorMessage(HttpServletResponse response){
        Map<String, Object> errorMessage = new HashMap<String, Object>();

        errorMessage.put("Mensaje", "Verifique los datos enviados");
        errorMessage.put("tipo", "error");

        return errorMessage;
    }

    public String postular(Integer idAsi, Integer idEmp, Integer IdEst){
        String resp = "";
        Postulaciones postulaciones = new Postulaciones();
        Postulaciones postulado = new Postulaciones();
        postulaciones.setAsiId(idAsi);
        postulaciones.setEmpId(idEmp);
        postulaciones.setEstId(IdEst);
        postulaciones.setPosFecha(new Date());
        postulaciones.setPosEstatus(ENVIADO); // 1 : Enviado
        postulado = postulacionesRepository.save(postulaciones);
        if (postulado != null){
            resp = "Se ha postulado Exitosamente";
        }else{
            resp = "Ha ocurrido un error";
        }
        return resp;
    }
    public HashMap<String,String> mapearDetalleAsignatura ( Object[] detAsig){
        HashMap<String,String> objAsig = new HashMap<>();
        Object[] aux = (Object[]) detAsig[0];
        objAsig.put("id",aux[0].toString());
        objAsig.put("titulo",aux[1].toString());
        objAsig.put("descripcion",aux[2].toString());
        objAsig.put("lugar",aux[3].toString());
        objAsig.put("tipo",aux[4].toString());
        objAsig.put("empId",aux[5].toString());
        objAsig.put("nombreEmp",aux[6].toString());
        return objAsig;

    }


}
