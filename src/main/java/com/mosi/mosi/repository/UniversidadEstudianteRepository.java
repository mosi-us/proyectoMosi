package com.mosi.mosi.repository;

import com.mosi.mosi.bean.UniversidadEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversidadEstudianteRepository extends JpaRepository<UniversidadEstudiante,Integer> {
    String SQL_CONSULTA_UNIVERSIDAD = "SELECT UNI_ID FROM UNE_UniversidadEstudiante WHERE EST_Id = :idEst ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_UNIVERSIDAD)
    List<Integer> consultaUniversidadEstudiante(@Param("idEst") Integer idEst);
}
