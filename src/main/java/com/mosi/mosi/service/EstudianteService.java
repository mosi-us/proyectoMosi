package com.mosi.mosi.service;

import com.mosi.mosi.bean.*;
import com.mosi.mosi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.mosi.mosi.constantes.constante.ENVIADO;
import static com.mosi.mosi.constantes.constante.TITULO_NOTIFICACION_POSTULACION;

@Service
public class EstudianteService {
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
    PasionesMaestroRepository pasionesMaestroRepository;
    @Autowired
    InvestigacionesRepository investigacionesRepository;
    @Autowired
    PasatiempoMaestroRepository pasatiempoMaestroRepository;
    @Autowired
    PostulacionesRepository postulacionesRepository;
    @Autowired
    HabilidadesBlandasMaestroRepository habilidadesBlandasMaestroRepository;
    @Autowired
    NotificacionesRepository notificacionesRepository;
    @Autowired
    SoftwareTecnologiaMaestroRepository softwareTecnologiaMaestroRepository;
    @Autowired
    AsignaturaRepository asignaturaRepository;

public Estudiante guardarEstudiante(String nombresEstudiante, String apellidosEstudiante,
                                    Integer fechaNac, Integer pais,Integer ciudad,String tlf,String codPais,String correo, List<?>deporte,
                                    List<?> idioma, Integer universidad, Integer carrera, Integer usuario,Integer semestre,
                                    List<?> pasatiempo,String descripcion,List<?>softTecn,List<?>habilidades){
    Estudiante estudiante = new Estudiante();
    DeporteMaestro dep =new DeporteMaestro();
    IdiomaMaestro idi =new IdiomaMaestro();
    PasatiempoMaestro hom =new PasatiempoMaestro();
    SoftwareTecnologiasMaestro syt = new SoftwareTecnologiasMaestro();
    HabilidadesBlandasMaestro ham= new HabilidadesBlandasMaestro();


    if ((!nombresEstudiante.isEmpty()) && (!apellidosEstudiante.isEmpty()) && (fechaNac != null)
            &&(pais != null) && (carrera != null) && (usuario!=null)){
        estudiante.setNombre(nombresEstudiante);
        estudiante.setApellido(apellidosEstudiante);
        estudiante.setFechaNac(fechaNac);
        estudiante.setIdpai(pais);
        estudiante.setUsuid(usuario);
        estudiante.setIdCar(carrera);
        estudiante.setTelefono(tlf);
        estudiante.setCodigoPais(codPais);
        estudiante.setIdCiudad(ciudad);
        estudiante.setCorreo(correo);
        estudiante.setDescripcion(descripcion);
        if (universidad!=null) {
            estudiante.setIdUni(universidad);
        }
        estudiante.setEstPrincipal(1);
        if (semestre!=null) {
            estudiante.setSemestre(semestre);
        }
        estudiante = estudianteRepository.save(estudiante);
        int est = estudiante.getId();
        if (estudiante.getId()!=null) {
            if (deporte != null) {
                for (int i = 0; i < deporte.size(); i++) { //guardando deporte en tabla maestra
                    dep.setDepIdDeporte(Integer.valueOf(deporte.get(i).toString()));
                    dep.setEstIdDep(est);
                    deporteMaestroRepository.save(dep);
                    dep = new DeporteMaestro();
                }
            }
            if (idioma != null) {
                for (int i = 0; i < idioma.size(); i++) { //guardando idioma en tabla maestra
                    idi.setIdiIdIdioma(Integer.valueOf(((LinkedHashMap) idioma.get(i)).get("idIdioma").toString()));
                    idi.setEstIDIdioma(est);
                    idi.setNivel(Integer.valueOf(((LinkedHashMap) idioma.get(i)).get("nivel").toString()));
                    idiomaMaestroRepository.save(idi);
                    idi = new IdiomaMaestro();
                }
            }
            if (pasatiempo != null) {
                for (int i = 0; i < pasatiempo.size(); i++) { //guardando Pasatiempo en tabla maestra
                    hom.setHobId(Integer.valueOf(pasatiempo.get(i).toString()));
                    hom.setEstId(est);
                    pasatiempoMaestroRepository.save(hom);
                    hom = new PasatiempoMaestro();
                }
            }
            if (softTecn !=null) {
                for (int i = 0; i < softTecn.size(); i++) { //guardando sofytecn en tabla maestra
                    syt.setIdSyt(Integer.valueOf(((LinkedHashMap) softTecn.get(i)).get("idSyt").toString()));
                    syt.setNivel(Integer.valueOf(((LinkedHashMap) softTecn.get(i)).get("nivel").toString()));
                    syt.setEstId(est);
                    softwareTecnologiaMaestroRepository.save(syt);
                    syt = new SoftwareTecnologiasMaestro();
                }
            }
            if (habilidades !=null) {
                for (int i = 0; i < habilidades.size(); i++) { //guardando sofytecn en tabla maestra
                    ham.setHabId(Integer.valueOf(habilidades.get(i).toString()));
                    ham.setEstId(est);
                    habilidadesBlandasMaestroRepository.save(ham);
                    ham = new HabilidadesBlandasMaestro();
                }
            }
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
        List<Object[]> idiId = idiomaMaestroRepository.consultaIdiomaEstudiante(idEst);
        List<HashMap<String, String>> resulIdi= this.listado(idiId);


        /*pasatiempo*/
        List<Object[]> pasId = pasatiempoMaestroRepository.consultaPasatiempoMaestroEstudiante(idEst);
        List<HashMap<String, String>> resultPas= this.listado(pasId);

        /*habilidades blandas*/
        List<Object[]> habs = habilidadesBlandasMaestroRepository.consultarHablidadesPorEstudiante(idEst);
        List<HashMap<String, String>> resultHab= this.listado(habs);

        /** Software y Tecnologias*/
        List<Object[]> syts = softwareTecnologiaMaestroRepository.consultarSotfwareyTecnEstudiante(idEst);
        List<HashMap<String, String>> resultSyt= this.listado(syts);

        List<List<HashMap<String, String>>> result= new ArrayList<>();
        result.add(perfilEstudiante);
        result.add(resulDepo);
        result.add(resulIdi);
        result.add(resultPas);
        result.add(resultHab);
        result.add(resultSyt);

        return result;

    }

    public static List<HashMap<String,String>> listado(List<Object[]> resul) {
        HashMap<String,String> objList= new HashMap<String, String>();
        List<HashMap<String, String>> resulobj= new ArrayList<>();
        for (int i =0;i<resul.size();i++){
            objList.put("id",resul.get(i)[0].toString());
            objList.put("Nombre",resul.get(i)[1].toString());
            if (resul.get(i).length>2){
                objList.put("nivel",resul.get(i)[2].toString());
            }
            resulobj.add(objList);
            objList = new HashMap<String, String>();
        }

        return resulobj;
    }

    public  List<HashMap<String,String>>construirEstudiante(List<Object[]> perfil) {
        List<HashMap<String,String>> perfilEstudiante = new ArrayList<>(); ;
        HashMap<String,String> listPerfil;

        listPerfil = this.doConstruirEstudiante(perfil);

        perfilEstudiante.add(listPerfil);

        return perfilEstudiante;
    }


    public HashMap<String,String> doConstruirEstudiante(List<Object[]> perfil) {
        HashMap<String, String> listPerfil = new HashMap<String, String>();

        listPerfil.put("nombre", perfil.get(0)[0].toString());
        listPerfil.put("apellido", perfil.get(0)[1].toString());
        listPerfil.put("FechaNac", perfil.get(0)[2].toString());
        listPerfil.put("idUsuario", perfil.get(0)[3].toString());
        listPerfil.put("nombreUsuario", perfil.get(0)[4].toString());
        listPerfil.put("idEstudiante", perfil.get(0)[5].toString());
        listPerfil.put("idPais",perfil.get(0)[6].toString());
        listPerfil.put("pais",perfil.get(0)[7].toString());
        listPerfil.put("semestre",perfil.get(0)[8].toString());
        listPerfil.put("idCarrera",perfil.get(0)[9].toString());
        listPerfil.put("carrera",perfil.get(0)[10].toString());
        listPerfil.put("idUni",perfil.get(0)[11].toString());
        listPerfil.put("universidad",perfil.get(0)[12].toString());
        listPerfil.put("descripcion",perfil.get(0)[13].toString());
        listPerfil.put("correo",perfil.get(0)[14].toString());
        listPerfil.put("telefono",perfil.get(0)[15].toString());
        listPerfil.put("idciu",perfil.get(0)[16].toString());
        listPerfil.put("ciudad",perfil.get(0)[17].toString());
        listPerfil.put("lugar",perfil.get(0)[18].toString());

        return listPerfil;
    }

    public static Map<String, Object> commonErrorMessage(HttpServletResponse response){
        Map<String, Object> errorMessage = new HashMap<String, Object>();

        errorMessage.put("Mensaje", "Verifique los datos enviados");
        errorMessage.put("tipo", "error");

        return errorMessage;
    }

    public String postular(Integer idAsi, Integer idEmp, Integer IdEst){

    /**
     * Agregar logica:
     * cuando el correo usado para registro este asociado a mosi se le dara acceso ilimitado al estudiante para usar la plataforma, de lo
     * contrario solo se le dara un año gratis.
     * */
        String resp = "";
        Postulaciones postulaciones = new Postulaciones();
        Postulaciones postulado = new Postulaciones();
        postulaciones.setAsiId(idAsi);
        postulaciones.setEmpId(idEmp);
        postulaciones.setEstId(IdEst);
        postulaciones.setPosFecha(new Date());
        postulaciones.setPosEstatus(ENVIADO); // 1 : Enviado
        postulado = postulacionesRepository.save(postulaciones);
        /*guardo notificacion*/
        Notificaciones not = new Notificaciones();
        Optional<Estudiante> est = estudianteRepository.findById(IdEst);
        Estudiante estudiante = est.get();
        String nombreEst = estudiante.getNombre() + " " + estudiante.getApellido();
        Asignatura desafio_practica = asignaturaRepository.findByAsiId(idAsi);
        String nombreAsig = desafio_practica.getAsiTitulo();
        not.setNotTitulo(nombreEst + TITULO_NOTIFICACION_POSTULACION + nombreAsig);
        not.setNotEstatus(ENVIADO);
        not.setNotFechaEnvio(new Date());
        not.setNotIdRemitente(IdEst);
        not.setNotIdDestino(idEmp);
        Notificaciones env_not = notificacionesRepository.save(not);

        if ((postulado != null) && (not != null)){
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

   public List<HashMap<String,Object>> listarDetalleEstudiantes (List<DetalleEstudiante> det_estudiante){
       HashMap<String,String> detalleEstudianteEmp = new HashMap<>();
       List<Integer> idsDet = new ArrayList<>();
       HashMap<String,String> listaEspSemCarr = new HashMap<>();
       List<HashMap<String,String>> perfilA = new ArrayList();
       List<HashMap<String,Object>> ListaCompleta = new ArrayList();


       List<List<HashMap<String,Object>>> perfilB = new ArrayList();
       List<HashMap<String,String>> perfildeporte = new ArrayList();
       HashMap<String,String> depLista = new HashMap<>();
       HashMap<String,String> idiLista = new HashMap<>();
       HashMap<String,String> habLista = new HashMap<>();
       HashMap<String,String> sytLista = new HashMap<>();
       for (int i = 0; i < det_estudiante.size(); i++) {
           detalleEstudianteEmp = this.buildPerfildetalleEstudiante(det_estudiante.get(i));
           String g = detalleEstudianteEmp.get("id");
           idsDet.add(Integer.valueOf(g));
           g = "";
       }

       /*listado deporte empresa*/
       List<Object[]> dep_Empresa = deporteMaestroRepository.consultar_deporte_estudiante_empresa(idsDet); /** 0: id deporte 1: id detalle estudiante*/
       List<Object[]> idi_Empresa = idiomaMaestroRepository.consultar_idioma_estudiante_empresa(idsDet);/** 0: id idioma 1: id detalle estudiante 2: nivel idioma*/
       List<Object[]> hab_Empresa = habilidadesBlandasMaestroRepository.consultar_habilidades_estudiante_Empresa(idsDet); /** 0: id hab 1: id detalle estudiante*/
       List<Object[]> syt_Empresa = softwareTecnologiaMaestroRepository.consultar_syt_estudiante_empresa(idsDet);/** 0: id idioma 1: id detalle estudiante 2: nivel idioma*/

       for (int i = 0; i < det_estudiante.size(); i++) {//contruir array de detalle de estudinte
           detalleEstudianteEmp = this.buildPerfildetalleEstudiante(det_estudiante.get(i));
           Object obj = ((Object) det_estudiante.get(i));
           HashMap<String,Object> obj2 = new HashMap<>();
           obj2.put("estudiante",obj);
           perfilA = new ArrayList<>();
           depLista = new HashMap<>();
           idiLista = new HashMap<>();
           perfilA.add(detalleEstudianteEmp);
           int n=1;

           String id_det_empresa = det_estudiante.get(i).getId().toString();

           for (int w = 0; w < dep_Empresa.size(); w++) { //deporte
               String id_dep_empresa = dep_Empresa.get(w)[1].toString();//id del detalle
               if (id_det_empresa.equals(id_dep_empresa)) {
                   depLista.put("idDep"+n,dep_Empresa.get(w)[0].toString()); // id del deporte
                   n++;
               }
           }
           Object objdep = ((Object) depLista);
           obj2.put("deporte",objdep);
           n=1;
           for (int w = 0; w < idi_Empresa.size(); w++) {//idioma
               String id_idioma_empresa = idi_Empresa.get(w)[1].toString();//id del detalle
               if (id_det_empresa.equals(id_idioma_empresa)) {
                   idiLista.put("idIdi"+n,idi_Empresa.get(w)[0].toString()); // id del idioma
                   idiLista.put("nivel"+n,idi_Empresa.get(w)[2].toString());
                   n++;
               }
           }
           Object objIdi = ((Object) idiLista);
           obj2.put("idioma",objIdi);
           n=1;
           for (int w = 0; w < hab_Empresa.size(); w++) { //habilidad
               String id_Hab_empresa = hab_Empresa.get(w)[1].toString();//id del detalle
               if (id_det_empresa.equals(id_Hab_empresa)) {
                   habLista.put("idhab"+n,hab_Empresa.get(w)[0].toString()); // id del habilidad
                   n++;
               }
           }
           Object objHab = ((Object) habLista);
           obj2.put("habilidades",objHab);
           n=1;
           for (int w = 0; w < syt_Empresa.size(); w++) {//Software y Tecnologias
               String id_syt_empresa = syt_Empresa.get(w)[1].toString();//id del detalle
               if (id_det_empresa.equals(id_syt_empresa)) {
                   sytLista.put("idSyt"+n,syt_Empresa.get(w)[0].toString()); // id del syt
                   sytLista.put("nivel"+n,syt_Empresa.get(w)[2].toString());
                   n++;

               }
           }
           Object objSyt = ((Object) sytLista);
           obj2.put("softYtecn",objSyt);

           perfilA.add(depLista);
           perfilA.add(idiLista);
           perfilA.add(habLista);
           perfilA.add(sytLista);
           ListaCompleta.add(obj2);
           perfilB.add(ListaCompleta);
           //perfilB.add(perfilA);
       }
    return ListaCompleta;
   }

    public  HashMap<String,String> buildPerfildetalleEstudiante(DetalleEstudiante perfil_empresa){
        HashMap<String,String> detalle = new HashMap<>();
        detalle.put("id",perfil_empresa.getId().toString());
        if (perfil_empresa.getDescripcion()!=null) {
            detalle.put("descripcion",perfil_empresa.getDescripcion().toString());
        }
        detalle.put("asiID", perfil_empresa.getAsignatura().toString());
        detalle.put("empID", perfil_empresa.getIdEmp().toString());
        detalle.put("paiId",perfil_empresa.getIdpai().toString());

        if (perfil_empresa.getSemestre()!=null) {
            detalle.put("det_sem", perfil_empresa.getSemestre().toString());
        }
        detalle.put("carId",perfil_empresa.getIdCar().toString());
        if (perfil_empresa.getIdUni()!=null) {
            detalle.put("uniID", perfil_empresa.getIdUni().toString());
        }
        detalle.put("asiId",perfil_empresa.getAsignatura().toString());

        return detalle;
    }
}
