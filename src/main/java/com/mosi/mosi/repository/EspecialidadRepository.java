package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EspecialidadRepository extends JpaRepository<Especialidad,String> {
    List<Especialidad> findAllByIdGreaterThan(Integer zero);

    String SQL_CONSULTA_NOMBRE_ESPECIALIDAD = "SELECT * FROM ESP_Especialidad WHERE ESP_ID in :idEsp ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_NOMBRE_ESPECIALIDAD)
    List<Object[]> consultaEspecialidadEstudiante(@Param("idEsp") List<Integer> idEsp);
}
