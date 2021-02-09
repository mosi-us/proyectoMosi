package com.mosi.mosi.repository;

import com.mosi.mosi.bean.IdiomaMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IdiomaMaestroRepository extends JpaRepository<IdiomaMaestro,Integer> {
    String SQL_CONSULTA_IDIOMA_ESTUDIANTE = "SELECT idi.IDI_Id,IDI_Nombre,IIM.IDE_Nivel FROM IDI_Idiomas idi " +
                                            "inner join IDE_IdiomaMaestro IIM on IDI.IDI_Id = IIM.IDI_Id " +
                                            "WHERE IIM.EST_Id in (:idEst) ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_IDIOMA_ESTUDIANTE)
    List<Object[]> consultaIdiomaEstudiante(@Param("idEst") Integer idEst);

    String SQL_CONSULTA_IDIOMA_EMPRESA = "SELECT IDI_ID,DET_Id,IDE_Nivel FROM IDE_IdiomaMaestro WHERE DET_Id IN (:idDet)";
    @Query(nativeQuery = true, value = SQL_CONSULTA_IDIOMA_EMPRESA)
    List<Object[]> consultar_idioma_estudiante_empresa(@Param("idDet") List<Integer> idDet);

}
