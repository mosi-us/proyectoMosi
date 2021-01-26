package com.mosi.mosi.repository;
import com.mosi.mosi.bean.DeporteMaestro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeporteMaestroRepository extends CrudRepository<DeporteMaestro,Integer> {

String SQL_CONSULTA_DEPORTE = "SELECT DEP_ID FROM DEE_DeporteMaestro WHERE EST_Id = :idEst ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_DEPORTE)
    List<Integer> consultaEstudiante(@Param("idEst") Integer idEst);

    String SQL_CONSULTA_DEPORTE_ESTUDIANTE_EMPRESA = "SELECT DEP_Id,DET_Id FROM DEE_DeporteMaestro WHERE DET_Id in (:idDet)";
    @Query(nativeQuery = true, value = SQL_CONSULTA_DEPORTE_ESTUDIANTE_EMPRESA)
    List<Object[]> consultar_deporte_estudiante_empresa(@Param("idDet") List<Integer> idEst);
}
