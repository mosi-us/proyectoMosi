package com.mosi.mosi.repository;

import com.mosi.mosi.bean.SoftwareTecnologiasMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SoftwareTecnologiaMaestroRepository extends JpaRepository<SoftwareTecnologiasMaestro,Integer> {
    String SQL_CONSULTA_SOFTWARE_TECNOLOGIA = "select SST.SYT_Id,SST.SYT_Nombre,SSTM.STM_nivel from STM_SoftwareTecnologiasMaestro SSTM " +
            "inner join SYT_SoftwareTecnologias SST on SSTM.SYT_Id = SST.SYT_Id " +
            "WHERE SSTM.EST_Id =:idEst";
    @Query(nativeQuery = true, value = SQL_CONSULTA_SOFTWARE_TECNOLOGIA)
    List<Object[]> consultarSotfwareyTecnEstudiante(@Param("idEst") Integer idEst);


    String SQL_CONSULTA_SOFTWARE_TECNOLOGIA_EMPRESA="select SYT_Id,DET_Id,STM_Nivel from STM_SoftwareTecnologiasMaestro WHERE DET_Id IN (:idDet)";
    @Query(nativeQuery = true, value = SQL_CONSULTA_SOFTWARE_TECNOLOGIA_EMPRESA)
    List<Object[]>consultar_syt_estudiante_empresa(@Param("idDet") List<Integer> idDet);
}
