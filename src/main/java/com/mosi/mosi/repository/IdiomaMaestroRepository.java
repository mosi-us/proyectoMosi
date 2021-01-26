package com.mosi.mosi.repository;

import com.mosi.mosi.bean.IdiomaMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IdiomaMaestroRepository extends JpaRepository<IdiomaMaestro,Integer> {
    String SQL_CONSULTA_IDIOMA_ESTUDIANTE = "SELECT IDI_ID FROM IDE_IdiomaMaestro WHERE EST_Id in (:idEst) ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_IDIOMA_ESTUDIANTE)
    List<Integer> consultaIdiomaEstudiante(@Param("idEst") Integer idEst);

    String SQL_CONSULTA_IDIOMA_EMPRESA = "SELECT IDI_ID,DET_Id FROM IDE_IdiomaMaestro WHERE DET_Id IN (:idDet)";
    @Query(nativeQuery = true, value = SQL_CONSULTA_IDIOMA_EMPRESA)
    List<Object[]> consultar_idioma_estudiante_empresa(@Param("idDet") List<Integer> idDet);

}
