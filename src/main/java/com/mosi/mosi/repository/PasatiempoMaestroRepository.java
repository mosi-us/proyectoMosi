package com.mosi.mosi.repository;

import com.mosi.mosi.bean.PasatiempoMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasatiempoMaestroRepository extends JpaRepository<PasatiempoMaestro,Integer> {
    String SQL_CONSULTA_PASATIEMPO_ESTUDIANTE = "select HOM_Id from HOM_PasatiempoMaestro WHERE EST_Id in (:idEst) ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_PASATIEMPO_ESTUDIANTE)
    List<Integer> consultaPasatiempoMaestroEstudiante(@Param("idEst") Integer idEst);
}
