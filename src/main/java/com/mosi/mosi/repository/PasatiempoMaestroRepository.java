package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.PasatiempoMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasatiempoMaestroRepository extends JpaRepository<PasatiempoMaestro,Integer> {
    String SQL_CONSULTA_PASATIEMPO_ESTUDIANTE = "select * from HOM_PasatiempoMaestro HOM " +
                                                "WHERE HOM.EST_Id = :idEst ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_PASATIEMPO_ESTUDIANTE)
    List<PasatiempoMaestro> consultaPasatiempoMaestroEstudiante(@Param("idEst") Integer idEst);

    List<PasatiempoMaestro> findByEstudiante(Estudiante estudiante);
}
