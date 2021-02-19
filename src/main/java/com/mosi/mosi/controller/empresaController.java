package com.mosi.mosi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosi.mosi.bean.*;
import com.mosi.mosi.repository.AsignaturaRepository;
import com.mosi.mosi.repository.EstudianteRepository;
import com.mosi.mosi.repository.PreguntasRepository;
import com.mosi.mosi.service.EstudianteService;
import com.mosi.mosi.service.UserService;
import com.mosi.mosi.service.empresaService;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mosi.mosi.constantes.constante.*;

@RestController
public class empresaController {
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
        if (descripcion!= null && rubro!= null && ubicacion!=null && pais!=null && sitioW!=null && codigoPais!=null && correo!=null && nombre!=null && razonSocial!=null
                && telefono!=null && mision!=null && vision!=null){
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

    @PostMapping("/AgregarDesafio_Practica")
    public List<HashMap<String,Object>> AgregarDesafio_Practica(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);

        String titulo = (params.containsKey(TITULO_ASI) && params.get(TITULO_ASI) != null && !params.get(TITULO_ASI).toString().isEmpty())
                ? params.get(TITULO_ASI).toString() : null;
        String descripcion_asi = (params.containsKey(DESCRIPCION_ASI) && params.get(DESCRIPCION_ASI) != null && !params.get(DESCRIPCION_ASI).toString().isEmpty())
                ? params.get(DESCRIPCION_ASI).toString() : null;
        Integer lugar 	= (params.containsKey(LUGAR) && params.get(LUGAR) != null && !params.get(LUGAR).toString().isEmpty())
                ? Integer.valueOf(params.get(LUGAR).toString()) : null;
        List<?> preguntas = (params.containsKey(PREGUNTAS) &&  params.get(PREGUNTAS) != null) ? UserService.convertObjectToList(params.get(PREGUNTAS)) : null;
        Integer tipo 	= (params.containsKey(TIPO_ASI) && params.get(TIPO_ASI) != null && !params.get(TIPO_ASI).toString().isEmpty())
                ? Integer.valueOf(params.get(TIPO_ASI).toString()) : null;
        Integer empId 	= (params.containsKey(ID_EMPRESA) && params.get(ID_EMPRESA) != null && !params.get(ID_EMPRESA).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_EMPRESA).toString()) : null;
        /*parametros para caracteristicas de estudiante deseado*/
        String descripcion = (params.containsKey(DESCRIPCION_EST) && params.get(DESCRIPCION_EST) != null && !params.get(DESCRIPCION_EST).toString().isEmpty())
                ? params.get(DESCRIPCION_EST).toString() : null;
        Integer pais 	= (params.containsKey(PAIS_ID) && params.get(PAIS_ID) != null && !params.get(PAIS_ID).toString().isEmpty())
                ? Integer.valueOf(params.get(PAIS_ID).toString()) : null;
        Integer semestre = (params.containsKey(SEMESTRE) && params.get(SEMESTRE) != null && !params.get(SEMESTRE).toString().isEmpty())
                ? Integer.valueOf(params.get(SEMESTRE).toString()) : null;
        Integer carrera = (params.containsKey(CARRERA_ID) && params.get(CARRERA_ID) != null && !params.get(CARRERA_ID).toString().isEmpty())
                ? Integer.valueOf(params.get(CARRERA_ID).toString()) : null;
        Integer universidad = (params.containsKey(UNIVERSIDAD_ID) && params.get(UNIVERSIDAD_ID) != null && !params.get(UNIVERSIDAD_ID).toString().isEmpty())
                ? Integer.valueOf(params.get(UNIVERSIDAD_ID).toString()) : null;
        List<?> deporte = (params.containsKey(DEPORTE_ID) &&  params.get(DEPORTE_ID) != null) ? UserService.convertObjectToList(params.get(DEPORTE_ID)) : null;
        List<?> idioma = (params.containsKey(IDIOMAS) &&  params.get(IDIOMAS) != null) ? UserService.convertObjectToList(params.get(IDIOMAS)) : null;
        List<?> softYTecn = (params.containsKey(SOFTWARE_TECNOLOGIA) &&  params.get(SOFTWARE_TECNOLOGIA) != null) ? UserService.convertObjectToList(params.get(SOFTWARE_TECNOLOGIA)) : null;
        List<?> hablidadesB = (params.containsKey(HABILIDADES) &&  params.get(HABILIDADES) != null) ? UserService.convertObjectToList(params.get(HABILIDADES)) : null;

        Asignatura asignatura = new Asignatura();
        Preguntas pregunta_asi = new Preguntas();
        DetalleEstudiante estudiante = new DetalleEstudiante();

        List<HashMap<String,Object>> result = new ArrayList<>();
        HashMap<String,Object> listAsi = new HashMap<>();
        HashMap<String,Object> listpreg = new HashMap<>();
        List<Object> pregList = new ArrayList<>();
        HashMap<String,Object> listDetEst = new HashMap<>();
        asignatura.setAsiTitulo(titulo);
        if (descripcion!=null){
            asignatura.setAsiDescripcion(descripcion);
        }
        asignatura.setAsiLugar(lugar);
        asignatura.setAsiTipo(tipo);
        asignatura.setEmpresa(empresaService.buscarEmpresaporId(empId));
        asignatura = asignaturaRepository.save(asignatura);
        listAsi.put("Asignatura",asignatura);
        if (asignatura!=null){
            for (int i=0; i<preguntas.size();i++){
                HashMap<String,String> p = (HashMap<String, String>) preguntas.get(i);
                pregunta_asi.setDecripcion(p.get("titulo"));
                pregunta_asi.setAsignatura(asignatura);
                pregunta_asi = preguntasRepository.save(pregunta_asi);
                pregList.add(pregunta_asi);
                pregunta_asi = new Preguntas();
            }
            for (int j=1;j<pregList.size();j++){
                listpreg.put("preguntas_" +j , pregList.get(j));
            }
            estudiante = empresaService.guardarEstudiante(pais,deporte,empId,asignatura.getAsiId(),idioma,universidad,carrera,semestre,descripcion,softYTecn,hablidadesB);
            listDetEst.put("caracteristicas",estudiante);
            result.add(listAsi);
            result.add(listDetEst);
            result.add(listpreg);
        }

        return result;
    }

    @PostMapping("/verDesafio_Practica")
    public HashMap<String,Object> verDesafio_Practica(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);

        Integer asignatura = (params.containsKey(ASIGNATURA) && params.get(ASIGNATURA) != null && !params.get(ASIGNATURA).toString().isEmpty())
                ? Integer.valueOf(params.get(ASIGNATURA).toString()) : null;


        HashMap<String,Object> result = empresaService.verDetallePractDesaf(asignatura);

        return result;
    }
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
        HashMap<String,Object> result = new HashMap<>();
        Estudiante perfil = estudianteRepository.findById(idEstudiante).get();
       // HashMap<String,Object> Estudiante =estudianteService.consultaEstudiante()
        List<Object> caracteristicas= estudianteService.consultaCaracteristicas(perfil,null,ESTUDIANTE);
        Estudiante estudiante = ((Estudiante) caracteristicas.get(0));
        String nombres = ((Estudiante) caracteristicas.get(0)).getNombre().toString();
        String apellidos = ((Estudiante) caracteristicas.get(0)).getApellido().toString();
        Integer dia, mes, año;
        año = Integer.valueOf((estudiante.getFechaNac().toString()).substring(0,4));
        mes =Integer.valueOf((estudiante.getFechaNac().toString()).substring(4,6));
        dia =Integer.valueOf((estudiante.getFechaNac().toString()).substring(6,8));
        Period edad = Period.between(LocalDate.of(año,mes,dia),LocalDate.now());
        result.put("Nombre y Apellido",nombres + " " + apellidos);
        result.put("Fecha de NAcimiento",dia+"/"+mes+"/"+año);
        result.put("Edad",edad.getYears());
        result.put("Pais",estudiante.getPais().getNombrePais());
        result.put("Ciudad",estudiante.getCiudad().getCiuNombre());
        String forma_de_trabajo = estudianteService.forma_de_trabajo(estudiante.getLugar());
        result.put("Forma de Trabajo",forma_de_trabajo);
        result.put("Informacion de Contanto:", estudiante.getCorreo().substring(0,4) + "*********.com -" + estudiante.getTelefono().substring(0,4) + "*******" );
        result.put("Semestre",estudiante.getSemestre());
        result.put("Deportes",caracteristicas.get(1));
        result.put("Idiomas",caracteristicas.get(2));
        result.put("Habilidades",caracteristicas.get(3));
        result.put("Pasatiempo",caracteristicas.get(4));
        result.put("Software y Tecnologias",caracteristicas.get(5));

        return result;
    }

    @PostMapping("/seleccionarEstudiante")
    public HashMap<String,Object> seleccionarEstudiante(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException, ParseException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idEstudiante = (params.containsKey(ID_ESTUDIANTE) && params.get(ID_ESTUDIANTE) != null && !params.get(ID_ESTUDIANTE).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_ESTUDIANTE).toString()) : null;
        Integer idAsignatura = (params.containsKey(ID_ASIGNATURA) && params.get(ID_ASIGNATURA) != null && !params.get(ID_ASIGNATURA).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_ASIGNATURA).toString()) : null;
        HashMap<String,Object> info_de_contacto_Estudiante = new HashMap<>();

        int seleccionar = empresaService.seleccionarEstudiante(idEstudiante,idAsignatura);

        if (seleccionar==1){

            Estudiante estudiante=estudianteRepository.findById(idEstudiante).get();
            Asignatura asignatura = asignaturaRepository.findByAsiId(idAsignatura);
            Boolean not = estudianteService.notificar(asignatura.getEmpresa().getId(),asignatura,TITULO_NOTIFICACION_SELECCION,ACEPTADO,idEstudiante,EMPRESA);
            info_de_contacto_Estudiante.put("Telefono: ", estudiante.getCodigoPais()+"-"+estudiante.getTelefono());
            info_de_contacto_Estudiante.put("Correo: ", estudiante.getCorreo());
            info_de_contacto_Estudiante.put("Nombre: ", estudiante.getNombre()+" "+ estudiante.getApellido());
        }
        return info_de_contacto_Estudiante;
    }
    @PostMapping("/verEstudiantesSeleccionados")
    public List<Postulaciones> verEstudiantesSeleccionados(@ApiBodyObject(clazz = String.class) @RequestBody String json) throws JsonProcessingException, ParseException {
        Map<String, Object> params = new ObjectMapper().readerFor(Map.class).readValue(json);
        Integer idAsignatura = (params.containsKey(ID_ASIGNATURA) && params.get(ID_ASIGNATURA) != null && !params.get(ID_ASIGNATURA).toString().isEmpty())
                ? Integer.valueOf(params.get(ID_ASIGNATURA).toString()) : null;

        List<Postulaciones> listaEstudiantesSeleccionados = empresaService.verEstudiantesSeleccionados(idAsignatura);

        return listaEstudiantesSeleccionados;
    }

    }
