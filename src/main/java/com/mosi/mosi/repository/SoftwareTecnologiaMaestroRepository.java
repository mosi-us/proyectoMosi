package com.mosi.mosi.repository;

import com.mosi.mosi.bean.DetalleEstudiante;
import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.SoftwareTecnologiasMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SoftwareTecnologiaMaestroRepository extends JpaRepository<SoftwareTecnologiasMaestro,Integer> {
    String SQL_CONSULTA_SOFTWARE_TECNOLOGIA = "select * from STM_SoftwareTecnologiasMaestro SSTM " +
                                              "WHERE SSTM.EST_Id =:idEst";
    @Query(nativeQuery = true, value = SQL_CONSULTA_SOFTWARE_TECNOLOGIA)
    List<SoftwareTecnologiasMaestro>consultarSotfwareyTecnEstudiante(@Param("idEst") Integer idEst);


    String SQL_CONSULTA_SOFTWARE_TECNOLOGIA_EMPRESA="select * from STM_SoftwareTecnologiasMaestro WHERE DET_Id IN (:idDet)";
    @Query(nativeQuery = true, value = SQL_CONSULTA_SOFTWARE_TECNOLOGIA_EMPRESA)
    List<SoftwareTecnologiasMaestro>consultar_syt_estudiante_empresa(@Param("idDet") List<Integer> idDet);

    List<SoftwareTecnologiasMaestro> findByDetalleEstudiante(DetalleEstudiante estudiante);
    List<SoftwareTecnologiasMaestro> findByEstudiante(Estudiante estudiante);


}
