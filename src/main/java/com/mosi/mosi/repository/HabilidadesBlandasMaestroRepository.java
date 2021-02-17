package com.mosi.mosi.repository;

import com.mosi.mosi.bean.DetalleEstudiante;
import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.HabilidadesBlandasMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabilidadesBlandasMaestroRepository extends JpaRepository<HabilidadesBlandasMaestro,Integer> {

    String SQL_CONSULTA_HABILIDADES_ESTUDIANTE = "select * from HAM_HabilidadesBlandasMaestro ham " +
                                                 "WHERE HAM.EST_Id =:idEst";
    @Query(nativeQuery = true, value = SQL_CONSULTA_HABILIDADES_ESTUDIANTE)
    List<HabilidadesBlandasMaestro> consultarHablidadesPorEstudiante(@Param("idEst") Integer idEst);

    String SQL_CONSULTA_HABILIDADES_ESTUDIANTE_EMPRESA= "SELECT * FROM HAM_HabilidadesBlandasMaestro WHERE DET_Id IN (:idDet)";
    @Query(nativeQuery = true, value = SQL_CONSULTA_HABILIDADES_ESTUDIANTE_EMPRESA)
    List<HabilidadesBlandasMaestro> consultar_habilidades_estudiante_Empresa(@Param("idDet") List<Integer> idDet);

    List<HabilidadesBlandasMaestro> findByDetalleEstudiante(DetalleEstudiante detalleEstudiante);
    List<HabilidadesBlandasMaestro> findByEstudiante(Estudiante estudiante);

}
