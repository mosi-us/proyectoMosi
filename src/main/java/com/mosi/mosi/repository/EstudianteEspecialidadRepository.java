package com.mosi.mosi.repository;

import com.mosi.mosi.bean.EstudianteEspecialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudianteEspecialidadRepository extends JpaRepository<EstudianteEspecialidad,Integer> {
    String SQL_CONSULTA_ESPECIALIDAD = "SELECT ESP_ID FROM ESE_Estudiante_Especialidad WHERE EST_Id = :idEst ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESPECIALIDAD)
    List<Integer> consultarEspecialidad(@Param("idEst") Integer idEst);
}
