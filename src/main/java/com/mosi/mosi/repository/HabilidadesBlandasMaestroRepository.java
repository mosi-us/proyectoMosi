package com.mosi.mosi.repository;

import com.mosi.mosi.bean.HabilidadesBlandasMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabilidadesBlandasMaestroRepository extends JpaRepository<HabilidadesBlandasMaestro,Integer> {

    String SQL_CONSULTA_HABILIDADES_ESTUDIANTE = "select HAB.HAB_id,HAB_Nombre from HAM_HabilidadesBlandasMaestro ham " +
            "inner join HAB_HabilidadesBlandas HAB on ham.HAB_Id = HAB.HAB_id " +
            "WHERE HAM.EST_Id =:idEst";
    @Query(nativeQuery = true, value = SQL_CONSULTA_HABILIDADES_ESTUDIANTE)
    List<Object[]> consultarHablidadesPorEstudiante(@Param("idEst") Integer idEst);

    String SQL_CONSULTA_HABILIDADES_ESTUDIANTE_EMPRESA= "SELECT HAB_ID,DET_Id FROM HAM_HabilidadesBlandasMaestro WHERE DET_Id IN (:idDet)";
    @Query(nativeQuery = true, value = SQL_CONSULTA_HABILIDADES_ESTUDIANTE_EMPRESA)
    List<Object[]> consultar_habilidades_estudiante_Empresa(@Param("idDet") List<Integer> idDet);

}
