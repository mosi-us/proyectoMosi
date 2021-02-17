package com.mosi.mosi.service;

import com.mosi.mosi.bean.*;
import com.mosi.mosi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.mosi.mosi.constantes.constante.*;

@Service
public class EstudianteService {
    @Autowired
    HabilidadesBlandasRepository habilidadesBlandasRepository;
    @Autowired
    SoftwareTecnologiaRepository softwareTecnologiaRepository;
    @Autowired
    paisService paisService;
    @Autowired
    universidadService universidadService;
    @Autowired
    ciudadService ciudadService;
    @Autowired
    carreraService carreraService;
    @Autowired
    UserService userService;
    @Autowired
    CiudadesRepository ciudadesRepository;
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
    PaisesRepository paisesRepository;
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
    @Autowired
    DetalleEstudianteRepository detalleEstudianteRepository;
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    empresaService empresaService;


public Estudiante guardarEstudiante(String nombresEstudiante, String apellidosEstudiante,
                                    Integer fechaNac, Integer pais,Integer ciudad,String tlf,String codPais,String correo, List<?>deporte,
                                    List<?> idioma, Integer universidad, Integer carrera, Integer usuario,Integer semestre,
                                    List<?> pasatiempo,String descripcion,List<?>softTecn,List<?>habilidades, Integer lugar){
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
        estudiante.setPais(paisService.findPaisbyId(pais));
        estudiante.setUsuario(userService.findUsersbyId(usuario));
        estudiante.setCarrera(carreraService.findCarreraById(carrera));
        estudiante.setTelefono(tlf);
        estudiante.setCodigoPais(codPais);
        estudiante.setCiudad(ciudadService.findCiudadById(ciudad));
        estudiante.setCorreo(correo);
        estudiante.setDescripcion(descripcion);
        if (universidad!=null) {
            estudiante.setUniversidad(universidadService.findUniversidadById(universidad));
        }
        estudiante.setEstPrincipal(1);
        if (semestre!=null) {
            estudiante.setSemestre(semestre);
        }
        estudiante.setLugar(lugar);
        estudiante = estudianteRepository.save(estudiante);
        int est = estudiante.getId();
        if (estudiante.getId()!=null) {
            Boolean saveCaracteristicas = this.guardarcaracteristicas(idioma,deporte,pasatiempo,habilidades,softTecn,estudiante,null,ESTUDIANTE);
        }

    }
    return estudiante;
}

public List<Object> consultaEstudiante(Integer usuario){
    List<Object> result = new ArrayList<>();
    result= this.consulta(usuario,0);
    return result;
}

    public  List<Object> consulta(Integer usuario,Integer est_id){
        List<Object> result= new ArrayList<>();


        Estudiante perfil = estudianteRepository.consultaPerfilActivo(usuario);
        if (perfil!=null) {
            Integer idEst = perfil.getId();

            result= this.consultaCaracteristicas(perfil,null,ESTUDIANTE);
        }
        return result;

    }
    public List<Object> consultaCaracteristicas(Estudiante estudiante,DetalleEstudiante detalleEstudiante,Integer tipo){
        List<Object> result= new ArrayList<>();
        Integer idEst= 0;
        HashMap<String,Object> dep = new HashMap<>();
        HashMap<String,Object> idi = new HashMap<>();
        HashMap<String,Object> hab = new HashMap<>();
        HashMap<String,Object> syt = new HashMap<>();
        HashMap<String,Object> hob = new HashMap<>();
        List<HashMap<String,Object>> sytE = new ArrayList<>();
        List<HashMap<String,Object>> idiomaE = new ArrayList<>();
        List<HashMap<String,Object>> deporteE = new ArrayList<>();
        List<HashMap<String,Object>> habilidadesE = new ArrayList<>();
        List<HashMap<String,Object>> pasatiempoE = new ArrayList<>();
        List<DeporteMaestro> deporteEstudiantes = new ArrayList<>();
        List<IdiomaMaestro> idiomaEstudiante  = new ArrayList<>();
        List<PasatiempoMaestro> pasatiempoEstudiante = new ArrayList<>();
        List<SoftwareTecnologiasMaestro> sytEstudiante = new ArrayList<>();
        List<HabilidadesBlandasMaestro> habilidadesEstudiante= new ArrayList<>();
        if (tipo==ESTUDIANTE) {
             result.add(estudiante);
             deporteEstudiantes= deporteMaestroRepository.findByEstudiante(estudiante);
             idiomaEstudiante = idiomaMaestroRepository.findByEstudiante(estudiante);
             pasatiempoEstudiante = pasatiempoMaestroRepository.findByEstudiante(estudiante);
             habilidadesEstudiante = habilidadesBlandasMaestroRepository.findByEstudiante(estudiante);
             sytEstudiante = softwareTecnologiaMaestroRepository.findByEstudiante(estudiante);

        }else if (tipo==EMPRESA){
            result.add(detalleEstudiante);
            deporteEstudiantes= deporteMaestroRepository.findByDetalleEstudiante(detalleEstudiante);
            idiomaEstudiante = idiomaMaestroRepository.findByDetalleEstudiante(detalleEstudiante);
            habilidadesEstudiante = habilidadesBlandasMaestroRepository.findByDetalleEstudiante(detalleEstudiante);
            sytEstudiante = softwareTecnologiaMaestroRepository.findByDetalleEstudiante(detalleEstudiante);
        }

        for (DeporteMaestro d:deporteEstudiantes) {
            dep.put("deporte",d.getDeporte());
            deporteE.add(dep);
            dep = new HashMap<>();
        }
        for (IdiomaMaestro i:idiomaEstudiante) {
            idi.put("idioma",i.getIdioma());
            idi.put("Nivel",i.getNivel());
            idiomaE.add(idi);
            idi= new HashMap<>();
        }
        for (PasatiempoMaestro p:pasatiempoEstudiante) {
            hob.put("pasatiempos",p.getPasatiempo());
            pasatiempoE.add(hob);
            hob =  new HashMap<>();
        }
        for (HabilidadesBlandasMaestro e:habilidadesEstudiante) {
            hab.put("Habilidades",e.getHabilidadesBlandas());
            habilidadesE.add(hab);
            hab = new HashMap<>();
        }
        for (SoftwareTecnologiasMaestro i:sytEstudiante) {
            syt.put("syt",i.getSoftwareTecnologias());
            syt.put("Nivel",i.getNivel());
            sytE.add(syt);
            syt= new HashMap<>();
        }
        result.add(deporteE);
        result.add(idiomaE);
        result.add(habilidadesE);
        result.add(pasatiempoE);
        result.add(sytE);
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
        postulaciones.setAsignatura(asignaturaRepository.findByAsiId(idAsi));
        postulaciones.setEmpresa(empresaService.buscarEmpresaporId(idEmp));
        postulaciones.setEstudiante(this.buscarEstudianteporId(IdEst));
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
           //detalleEstudianteEmp = this.buildPerfildetalleEstudiante(det_estudiante.get(i));
           String g = det_estudiante.get(i).getDetId().toString();
           idsDet.add(Integer.valueOf(g));
           g = "";
       }

       /*listado deporte empresa*/
       List<DeporteMaestro> dep_Empresa = deporteMaestroRepository.consultar_deporte_estudiante_empresa(idsDet); /** 0: id deporte 1: id detalle estudiante*/
       List<IdiomaMaestro> idi_Empresa = idiomaMaestroRepository.consultar_idioma_estudiante_empresa(idsDet);/** 0: id idioma 1: id detalle estudiante 2: nivel idioma*/
       List<HabilidadesBlandasMaestro> hab_Empresa = habilidadesBlandasMaestroRepository.consultar_habilidades_estudiante_Empresa(idsDet); /** 0: id hab 1: id detalle estudiante*/
       List<SoftwareTecnologiasMaestro> syt_Empresa = softwareTecnologiaMaestroRepository.consultar_syt_estudiante_empresa(idsDet);/** 0: id idioma 1: id detalle estudiante 2: nivel idioma*/

       for (int i = 0; i < det_estudiante.size(); i++) {//contruir array de detalle de estudinte
           Object obj = (det_estudiante.get(i));
           HashMap<String,Object> obj2 = new HashMap<>();
           obj2.put("estudiante",obj);
           perfilA = new ArrayList<>();
           depLista = new HashMap<>();
           idiLista = new HashMap<>();
           habLista = new HashMap<>();
           sytLista = new HashMap<>();
           perfilA.add(detalleEstudianteEmp);
           int n=1;

           String id_det_empresa = det_estudiante.get(i).getDetId().toString();

           for (int w = 0; w < dep_Empresa.size(); w++) { //deporte
               String id_dep_empresa = dep_Empresa.get(w).getDetalleEstudiante().getDetId().toString();//id del detalle
               if (id_det_empresa.equals(id_dep_empresa)) {
                   depLista.put("idDep"+n,dep_Empresa.get(w).getDeporte().getId().toString()); // id del deporte
                   n++;
               }
           }
           Object objdep = ((Object) depLista);
           obj2.put("deporte",objdep);
           n=1;
           for (int w = 0; w < idi_Empresa.size(); w++) {//idioma
               String id_idioma_empresa = idi_Empresa.get(w).getDetalleEstudiante().getDetId().toString();//id del detalle
               if (id_det_empresa.equals(id_idioma_empresa)) {
                   idiLista.put("idIdi"+n,idi_Empresa.get(w).getIdioma().getId().toString()); // id del idioma
                   idiLista.put("nivel"+n,idi_Empresa.get(w).getNivel().toString());
                   n++;
               }
           }
           Object objIdi = ((Object) idiLista);
           obj2.put("idioma",objIdi);
           n=1;
           for (int w = 0; w < hab_Empresa.size(); w++) { //habilidad
               String id_Hab_empresa = hab_Empresa.get(w).getDetalleEstudiante().getDetId().toString();//id del detalle
               if (id_det_empresa.equals(id_Hab_empresa)) {
                   habLista.put("idhab"+n,hab_Empresa.get(w).getHabilidadesBlandas().getHabId().toString()); // id del habilidad
                   n++;
               }
           }
           Object objHab = ((Object) habLista);
           obj2.put("habilidades",objHab);
           n=1;
           for (int w = 0; w < syt_Empresa.size(); w++) {//Software y Tecnologias
               String id_syt_empresa = syt_Empresa.get(w).getDetalleEstudiante().getDetId().toString();//id del detalle
               if (id_det_empresa.equals(id_syt_empresa)) {
                   sytLista.put("idSyt"+n,syt_Empresa.get(w).getSoftwareTecnologias().getSytId().toString()); // id del syt
                   sytLista.put("nivel"+n,syt_Empresa.get(w).getNivel().toString());
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

    /*public  HashMap<String,String> buildPerfildetalleEstudiante(DetalleEstudiante perfil_empresa){
        HashMap<String,String> detalle = new HashMap<>();
        detalle.put("id",perfil_empresa.getDetId().toString());
        if (perfil_empresa.getDetDescripcion()!=null) {
            detalle.put("descripcion",perfil_empresa.getDetDescripcion().toString());
        }
        detalle.put("asiID", perfil_empresa.getAsiId().toString());
        detalle.put("empID", perfil_empresa.getEmpId().toString());
        detalle.put("paiId",perfil_empresa.getPaiId().toString());

        if (perfil_empresa.getDetSem()!=null) {
            detalle.put("det_sem", perfil_empresa.getDetSem().toString());
        }
        detalle.put("carId",perfil_empresa.getCarId().toString());
        if (perfil_empresa.getUniId()!=null) {
            detalle.put("uniID", perfil_empresa.getUniId().toString());
        }
        detalle.put("asiId",perfil_empresa.getAsiId().toString());

        return detalle;
    }*/
    public Boolean guardar_Dep_idi_hab_syt(List<?> deporte,List<?> idioma, List<?>habilidades, List<?>softTecn,List<?>pasatiempo,Integer est,Integer tipo){
        DeporteMaestro dep =new DeporteMaestro();
        IdiomaMaestro idi =new IdiomaMaestro();
        PasatiempoMaestro hom =new PasatiempoMaestro();
        SoftwareTecnologiasMaestro syt = new SoftwareTecnologiasMaestro();
        HabilidadesBlandasMaestro ham= new HabilidadesBlandasMaestro();

        Estudiante estPerfil = estudianteRepository.findById(est).get();
        Boolean bandIdioma = false;
        Boolean bandDeporte = false;
        Boolean bandHabilidades = false;
        Boolean bandSyT = false;


        if (deporte != null) {
            for (int i = 0; i < deporte.size(); i++){
                Integer d = ((DeporteMaestro) deporte.get(0)).getDeporte().getId().intValue();
                Deporte depo = deporteRepository.findById(d).get();

                dep.setDeporte(depo);
                dep.setEstudiante(estPerfil);
                deporteMaestroRepository.save(dep);
                dep = new DeporteMaestro();
            }
        }

        if (idioma != null) {
            for (int j = 0; j < idioma.size(); j++) { //guardando idioma en tabla maestra
                Integer i = ((IdiomaMaestro) idioma.get(j)).getIdioma().getId().intValue();
                Idioma idio=idiomaRepository.findById(i).get();
                idi.setIdioma(idio);
                if ((tipo == 1)) { // estudiante
                    idi.setEstudiante(estPerfil);
                } else { // empresa
                    //idi.setDetalleEstudiante(est);
                }
                idi.setNivel(((IdiomaMaestro) idioma.get(j)).getNivel().intValue());
                idiomaMaestroRepository.save(idi);
                idi = new IdiomaMaestro();
            }
            bandIdioma=true;
        }
        if (pasatiempo != null) {
            for (int i = 0; i < pasatiempo.size(); i++) { //guardando Pasatiempo en tabla maestra
                Integer p =((PasatiempoMaestro) pasatiempo.get(0)).getPasatiempo().getId().intValue();

                Pasatiempo pasa = pasatiempoRepository.findById(p).get();
                hom.setPasatiempo(pasa);
                if ((tipo == 1)) { // estudiante
                    hom.setEstudiante(estPerfil);
                } else { // empresa
                    //hom.setDetId(est);
                }
                pasatiempoMaestroRepository.save(hom);
                hom = new PasatiempoMaestro();
            }
        }
        if (softTecn !=null) {
            for (int i = 0; i < softTecn.size(); i++) { //guardando sofytecn en tabla maestra
                Integer s= ((SoftwareTecnologiasMaestro) softTecn.get(i)).getSoftwareTecnologias().getSytId().intValue();
                SoftwareTecnologias sytE = softwareTecnologiaRepository.findById(s).get();
                syt.setSoftwareTecnologias(sytE);
                syt.setNivel(((SoftwareTecnologiasMaestro) softTecn.get(i)).getNivel().intValue());
                if ((tipo == 1)) { // estudiante
                    syt.setEstudiante(estPerfil);
                } else { // empresa
                   // syt.setDetId(est);
                }
                softwareTecnologiaMaestroRepository.save(syt);
                syt = new SoftwareTecnologiasMaestro();
            }
            bandSyT = true;
        }
        if (habilidades !=null) {
            for (int i = 0; i < habilidades.size(); i++) { //guardando sofytecn en tabla maestra
                Integer idHab = ((HabilidadesBlandasMaestro) habilidades.get(0)).getHabilidadesBlandas().getHabId().intValue();
                HabilidadesBlandas habi =  habilidadesBlandasRepository.findById(idHab).get();
                ham.setHabilidadesBlandas(habi);
                if ((tipo == 1)) { // estudiante
                    ham.setEstudiante(estPerfil);
                } else { // empresa
                    //ham.setDetId(est);
                }
                habilidadesBlandasMaestroRepository.save(ham);
                ham = new HabilidadesBlandasMaestro();
            }
            bandHabilidades= true;
        }
        if ((bandDeporte == true) && (bandHabilidades == true) && (bandIdioma==true) && (bandSyT==true)) {
            return true;
        }else {
            return false;
        }

    }
    public DetalleEstudiante guardarEstudiante(Integer pais, List<?> deporte, Integer empId, Integer asignatura, List<?> idioma,
                                               Integer universidad, Integer carrera, Integer semestre, String descripcion, List<?> softYTecn, List<?> hablidadesB){
        DetalleEstudiante detEstudiante = new DetalleEstudiante();
        DetalleEstudiante estudiante = new DetalleEstudiante();

        estudiante.setDetDescripcion(descripcion);
       /* estudiante.setAsiId(asignatura);
        estudiante.setEmpId(empId);
        estudiante.setPaiId(pais);
        estudiante.setDetSem(semestre);
        estudiante.setCarId(carrera);
        estudiante.setUniId(universidad);*/

        estudiante = detalleEstudianteRepository.save(estudiante);

        Boolean saveCaracteristicas = this.guardar_Dep_idi_hab_syt(deporte,idioma,hablidadesB,softYTecn,null,estudiante.getDetId(),EMPRESA);




        return estudiante;

    }
    public List<List<HashMap<String,Object>>> compatibilidad(List<HashMap<String,Object>>listadoCompleto,List<Object> perfil){
        List<List<HashMap<String,Object>>> result = new ArrayList<>();
        List<HashMap<String,Object>> asigList = new ArrayList<>();

        Integer semestre =((Estudiante) perfil.get(0)).getSemestre();;
        Integer idUni =((Estudiante) perfil.get(0)).getUniversidad().getId();
        List<HashMap<String,String>> deportesEstudiante = (List<HashMap<String, String>>) perfil.get(1);
        List<HashMap<String,String>> idiomasEstudiante = (List<HashMap<String, String>>) perfil.get(2);
        List<HashMap<String,String>> habilidadesEstudiante = (List<HashMap<String, String>>) perfil.get(3);
        List<HashMap<String,String>> sytEstudiante = (List<HashMap<String, String>>) perfil.get(5);
        List<List<HashMap<String, Object>>> listadoOrdenadoDetalle = new ArrayList<>();
        List<HashMap<String, Object>> list_por_items = new ArrayList<>();
        HashMap<String, Object> afinidad = new HashMap<>();
        Integer atributosEmpresa = 2; // inicia en 2 porque el valor de pais y carrera estan incluidos
        Integer atributosEstudiante = 2;
        Integer semestreEmpresa = 0;
        Integer semestreEstudiante = 0;
        Integer uniId_empresa = 0;
        for (int e = 0; e < listadoCompleto.size(); e++) {

            /*LISTADO DE PRACTICAS/ DESAFIOS*/
            DetalleEstudiante estEmpresa = (DetalleEstudiante) listadoCompleto.get(e).get("estudiante");
            Object[] listAsig = asignaturaRepository.consultaDetalleAsignatura(estEmpresa.getAsignatura().getAsiId());
            HashMap<String, Object> asignaturaEmpresa = new HashMap<>();
            asignaturaEmpresa.put("asignatura", listAsig);
            asigList.add(asignaturaEmpresa);
            if ((estEmpresa.getDetSem() != null)) {
                semestreEmpresa = Integer.valueOf(estEmpresa.getDetSem());
                atributosEmpresa = atributosEmpresa + 1;
            }
            if (estEmpresa.getUniversidad() != null) {
                uniId_empresa = Integer.valueOf(estEmpresa.getUniversidad().getId());
                atributosEmpresa = atributosEmpresa + 1;
            }

            Integer empId = estEmpresa.getEmpresa().getId();
            Integer lugarEmp = detalleEstudianteRepository.consultaLugarTrabajo(empId);
            if (lugarEmp!=null){
                atributosEmpresa = atributosEmpresa + 1;
            }
            Integer lugarEst = Integer.valueOf(((Estudiante) perfil.get(0)).getLugar());


            if ((semestre == semestreEmpresa) || (semestre > semestreEmpresa)) {

                atributosEstudiante = atributosEstudiante + 1;
            }
            if (uniId_empresa == idUni) {

                atributosEstudiante = atributosEstudiante + 1;
            }
            if (lugarEst == lugarEmp) {
                atributosEstudiante = atributosEstudiante + 1;
            }

            HashMap<String,String> idiomasEmpresa =(HashMap) listadoCompleto.get(e).get("idioma");
            if (idiomasEmpresa!= null && idiomasEmpresa.size() >0) {
                int size_idioma = idiomasEmpresa.size() / 2;
                for (int z = 0; z < size_idioma; z++) { // idiomas
                    Integer idiomaEmpresa_list = Integer.valueOf(idiomasEmpresa.get("idIdi" + (z + 1)));
                    Integer nivelIdiomaEmpresa = Integer.valueOf(idiomasEmpresa.get("nivel" + (z + 1)));
                    atributosEmpresa = atributosEmpresa + 1;
                    for (int x = 0; x < idiomasEstudiante.size(); x++) {
                        Object idiomasObj = (Object) idiomasEstudiante.get(x).get("idioma");
                        Integer idiomasEstudiante_list = ((Idioma) idiomasObj).getId().intValue();
                        HashMap<String, ?> idiomas = (HashMap) idiomasEstudiante.get(0);

                        Integer nivelIdiomaEstudiante = Integer.valueOf(idiomas.get("Nivel").toString());
                        if ((idiomaEmpresa_list == idiomasEstudiante_list) && ((nivelIdiomaEstudiante == nivelIdiomaEmpresa) || (nivelIdiomaEstudiante > nivelIdiomaEmpresa))) {
                            atributosEstudiante = atributosEstudiante + 1;
                        }
                    }
                }
            }
            HashMap<String,String> deportesEmpresa =(HashMap) listadoCompleto.get(e).get("deporte");
            if (deportesEmpresa!= null && deportesEmpresa.size() >0) {
                int size_deporte = deportesEmpresa.size();
                for (int z = 0; z < size_deporte; z++) { // deporte
                    Integer DeporteEmpresa_list = Integer.valueOf(deportesEmpresa.get("idDep" + (z + 1)));
                    atributosEmpresa = atributosEmpresa + 1;
                    for (int x = 0; x < deportesEstudiante.size(); x++) {
                        Object deporteObj = (Object) deportesEstudiante.get(x).get("deporte");
                        Integer DeporteEstudiante_list = ((Deporte) deporteObj).getId();
                        if (DeporteEmpresa_list == DeporteEstudiante_list) {
                            atributosEstudiante = atributosEstudiante + 1;
                        }
                    }
                }
            }
            HashMap<String,String> habilidadesEmpresa = (HashMap) listadoCompleto.get(e).get("habilidades");
            if (habilidadesEmpresa!= null && habilidadesEmpresa.size() >0) {
                int size_habilidades = habilidadesEmpresa.size();
                for (int z = 0; z < size_habilidades; z++) { // habilidades
                    Integer HabilidadesEmpresa_list = Integer.valueOf(habilidadesEmpresa.get("idhab" + (z + 1)));
                    atributosEmpresa = atributosEmpresa + 1;
                    for (int x = 0; x < habilidadesEstudiante.size(); x++) {
                        Object habilidadesObj = habilidadesEstudiante.get(x).get("Habilidades");
                        Integer HabilidadesEstudiante_list = ((HabilidadesBlandas) habilidadesObj).getHabId();
                        if (HabilidadesEmpresa_list == HabilidadesEstudiante_list) {
                            atributosEstudiante = atributosEstudiante + 1;
                        }
                    }
                }
            }
            HashMap<String,String> sytEmpresa = (HashMap) listadoCompleto.get(e).get("softYtecn");
            if (sytEmpresa!= null && sytEmpresa.size() >0) {
                int size_syt = sytEmpresa.size() / 2;
                for (int z = 0; z < size_syt; z++) { // Software y Tecnologias
                    Integer sytEmpresa_list = Integer.valueOf(sytEmpresa.get("idSyt" + (z + 1)));
                    Integer nivelSytEmpresa = Integer.valueOf(sytEmpresa.get("nivel" + (z + 1)));
                    atributosEmpresa = atributosEmpresa + 1;
                    for (int x = 0; x < sytEstudiante.size(); x++) {
                        Object sytObj = sytEstudiante.get(x).get("syt");
                        Integer sytEstudiante_list = ((SoftwareTecnologias) sytObj).getSytId().intValue();
                        HashMap<String, ?> syts = (HashMap) sytEstudiante.get(x);
                        Integer nivelSytEstudiante = Integer.valueOf(syts.get("Nivel").toString());
                        if ((sytEmpresa_list == sytEstudiante_list) && ((nivelSytEstudiante == nivelSytEstudiante) || (nivelSytEstudiante > nivelSytEmpresa))) {
                            atributosEstudiante = atributosEstudiante + 1;
                        }
                    }
                }
            }
            Integer puntaje = (atributosEstudiante * 100) / atributosEmpresa;
            afinidad.put("afinidad", puntaje.toString());
            list_por_items.add(listadoCompleto.get(e));

            list_por_items.add(afinidad);

            listadoOrdenadoDetalle.add(list_por_items);
            asigList.add(afinidad);
            result.add(asigList);
            asigList = new ArrayList<>();
            atributosEmpresa = 2;
            atributosEstudiante = 2;
            puntaje = 0;
            afinidad = new HashMap<>();
            list_por_items = new ArrayList<>();

        }

        return result;
    }
    public List<List<HashMap<String, Object>>> buscarCompatibilidad(Integer usuId, Integer estId,Integer tipo) {

        List<Object> perfil = this.consulta(usuId, estId);
        List<List<HashMap<String, Object>>> result = new ArrayList<>();
        if (perfil.size() > 0) {

            Integer idPais =((Estudiante) perfil.get(0)).getPais().getId();
            Paises pais = ((Estudiante) perfil.get(0)).getPais();
            Integer idCar = ((Estudiante) perfil.get(0)).getCarrera().getId();
            Carrera carrera = ((Estudiante) perfil.get(0)).getCarrera();
            Integer semestre =((Estudiante) perfil.get(0)).getSemestre();

            Integer atributosEmpresa = 2; // inicia en 2 porque el valor de pais y carrera estan incluidos
            Integer atributosEstudiante = 2;
            Integer semestreEmpresa = 0;
            Integer semestreEstudiante = 0;
            Integer uniId_empresa = 0;
            /**busqueda por carrera y pais*/
            List<DetalleEstudiante> det_estudiante = new ArrayList<DetalleEstudiante>();
            det_estudiante = detalleEstudianteRepository.consultar_estudiantes_empresa(idCar, idPais, semestre, tipo);
            List<HashMap<String,Object>> list = this.listarDetalleEstudiantes(det_estudiante);
            List<List<HashMap<String, Object>>> asigList = this.compatibilidad(list,perfil);




           result = asigList;

        }else {
        String msj = "sin resultados";

        HashMap<String,Object> mensaje = new HashMap<>();
        mensaje.put("mensaje",(Object) msj);
            List<HashMap<String, Object>> msjs = new ArrayList<>();
            msjs.add(mensaje);
        result.add(msjs);
    }
        return result;
    }

    public Estudiante buscarEstudianteporId(Integer IdEst){
    Estudiante estudiante = new Estudiante();
        estudiante= estudianteRepository.findById(IdEst).get();
        return estudiante;
    }


    public Boolean guardarcaracteristicas(List<?> list_Idi,List<?> list_Dep, List<?>list_Pasatiempo, List<?>list_Hab,List<?>list_Syt, Estudiante estudiante,DetalleEstudiante detalleEstudiante,Integer tipo){

        IdiomaMaestro idioma = new IdiomaMaestro();
        DeporteMaestro deporte = new DeporteMaestro();
        HabilidadesBlandasMaestro habilidad = new HabilidadesBlandasMaestro();
        SoftwareTecnologiasMaestro syt = new SoftwareTecnologiasMaestro();
        PasatiempoMaestro pasatiempo = new PasatiempoMaestro();

        for (int i=0;i<list_Idi.size();i++){
            HashMap<String,Integer> idi = ((HashMap) list_Idi.get(i));
            if (tipo==ESTUDIANTE){

            idioma.setEstudiante(estudiante);
            }else{
                idioma.setDetalleEstudiante(detalleEstudiante);

            }
            Integer idiomaId=idi.get("idIdioma");
            Idioma idiomaObj= idiomaRepository.findById(idiomaId).get();
            idioma.setIdioma(idiomaObj);
            idioma.setNivel(Integer.valueOf(idi.get("nivel")));
            idioma= idiomaMaestroRepository.save(idioma);
            idioma = new IdiomaMaestro();
        }
        for (int i=0;i<list_Dep.size();i++){
            Integer dep = ((Integer) list_Dep.get(i));
            if (tipo==ESTUDIANTE) {
                deporte.setEstudiante(estudiante);
            }else {
                deporte.setDetalleEstudiante(detalleEstudiante);
            }
            Deporte  deporteObj = deporteRepository.findById(dep).get();
            deporte.setDeporte(deporteObj);
            deporte= deporteMaestroRepository.save(deporte);
            deporte = new DeporteMaestro();
        }

        if (list_Pasatiempo!=null){
            for (int i=0;i<list_Pasatiempo.size();i++){
                Integer pas = (Integer) list_Pasatiempo.get(i);
                Pasatiempo pasatiempoObj= pasatiempoRepository.findById(pas).get();
                pasatiempo.setEstudiante(estudiante);
                pasatiempo.setPasatiempo(pasatiempoObj);
                pasatiempo= pasatiempoMaestroRepository.save(pasatiempo);
                pasatiempo = new PasatiempoMaestro();
            }
        }
        for (int i=0;i<list_Hab.size();i++){
            Integer hab = (Integer) list_Hab.get(i);
            if (tipo==ESTUDIANTE) {
                habilidad.setEstudiante(estudiante);
            }else{
                habilidad.setDetalleEstudiante(detalleEstudiante);
            }
            HabilidadesBlandas habilidadesObj = habilidadesBlandasRepository.findById(hab).get();
            habilidad.setHabilidadesBlandas(habilidadesObj);
            habilidad= habilidadesBlandasMaestroRepository.save(habilidad);
            habilidad = new HabilidadesBlandasMaestro();
        }
        for (int i=0;i<list_Syt.size();i++){
            HashMap<String,Integer> syt_ = (HashMap) list_Syt.get(i);
            if (tipo==ESTUDIANTE) {
                syt.setEstudiante(estudiante);
            }else {
                syt.setDetalleEstudiante(detalleEstudiante);
            }
            Integer sytId = syt_.get("idSyt").intValue();
            SoftwareTecnologias sytObj = softwareTecnologiaRepository.findById(sytId).get();
            syt.setSoftwareTecnologias(sytObj);
            syt.setNivel(syt_.get("nivel"));
            syt= softwareTecnologiaMaestroRepository.save(syt);
            syt = new SoftwareTecnologiasMaestro();
        }

    return true;
    }
}
