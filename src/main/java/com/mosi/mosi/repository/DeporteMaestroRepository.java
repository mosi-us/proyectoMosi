package com.mosi.mosi.repository;
import com.mosi.mosi.bean.DeporteMaestro;
import com.mosi.mosi.bean.DetalleEstudiante;
import com.mosi.mosi.bean.Estudiante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeporteMaestroRepository extends CrudRepository<DeporteMaestro,Integer> {

String SQL_CONSULTA_DEPORTE = "SELECT * FROM DEE_DeporteMaestro WHERE EST_Id = :idEst";
    @Query(nativeQuery = true, value = SQL_CONSULTA_DEPORTE)
    List<DeporteMaestro> consultaEstudiante(@Param("idEst") Integer idEst);

    String SQL_CONSULTA_DEPORTE_ESTUDIANTE_EMPRESA = "SELECT * FROM DEE_DeporteMaestro WHERE DET_Id in (:idDet)";
    @Query(nativeQuery = true, value = SQL_CONSULTA_DEPORTE_ESTUDIANTE_EMPRESA)
    List<DeporteMaestro> consultar_deporte_estudiante_empresa(@Param("idDet") List<Integer> idEst);

    List<DeporteMaestro> findByDetalleEstudiante(DetalleEstudiante estudiante);
    List<DeporteMaestro> findByEstudiante(Estudiante estudiante);


}
