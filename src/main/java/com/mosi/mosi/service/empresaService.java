package com.mosi.mosi.service;

import com.mosi.mosi.bean.*;
import com.mosi.mosi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.mosi.mosi.constantes.constante.EMPRESA;

@Service
public class empresaService {
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

    public HashMap<String,Object> verDetallePractDesaf(Integer idAsig){
        Asignatura asignatura;
        List<Idioma> idionaL = new ArrayList<>();
        List<Deporte> deporteL = new ArrayList<>();
        List<HabilidadesBlandas> habilidadesL = new ArrayList<>();
        List<SoftwareTecnologias> sytL = new ArrayList<>();
        HashMap<String,Object> result = new HashMap<>();
        asignatura= asignaturaRepository.findByAsiId(idAsig);
        result.put("asignatura",asignatura);
        DetalleEstudiante estudiante = detalleEstudianteRepository.findByAsignatura(asignatura);
        result.put("detalleEstudiante",estudiante);
        List<Object> caracteristicas = estudianteService.consultaCaracteristicas(null,estudiante,EMPRESA);
        result.put("Idioma",caracteristicas.get(1));
        result.put("deporte",caracteristicas.get(2));
        result.put("habilidades",caracteristicas.get(3));
        result.put("syt",caracteristicas.get(5));

        return result;
    }

}
