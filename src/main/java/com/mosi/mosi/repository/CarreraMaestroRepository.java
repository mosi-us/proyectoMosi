package com.mosi.mosi.repository;

import com.mosi.mosi.bean.CarreraUniversitariaMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarreraMaestroRepository extends JpaRepository<CarreraUniversitariaMaestro,Integer> {
    String SQL_CONSULTA_CARRERA = "SELECT CAR_ID FROM CAM_CarrerasUnivMaestro WHERE EST_Id = :idEst ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_CARRERA)
    List<Integer> consultaCarreraEstudiante(@Param("idEst") Integer idEst);




}
