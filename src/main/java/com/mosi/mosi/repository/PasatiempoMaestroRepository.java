package com.mosi.mosi.repository;

import com.mosi.mosi.bean.PasatiempoMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasatiempoMaestroRepository extends JpaRepository<PasatiempoMaestro,Integer> {
    String SQL_CONSULTA_PASATIEMPO_ESTUDIANTE = "select HP.HOB_Id,HP.HOB_Nombre from HOM_PasatiempoMaestro HOM\n" +
            "inner join HOB_Pasatiempo HP on HOM.HOB_Id = HP.HOB_Id\n" +
            "WHERE HOM.EST_Id = :idEst ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_PASATIEMPO_ESTUDIANTE)
    List<Object[]> consultaPasatiempoMaestroEstudiante(@Param("idEst") Integer idEst);
}
