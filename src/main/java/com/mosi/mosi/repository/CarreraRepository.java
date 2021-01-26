package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera,String> {
    List<Carrera> findAllByIdGreaterThan(Integer zero);
    String SQL_CONSULTA_NOMBRE_CARRERA = "SELECT * FROM CAR_CarrerasUniv WHERE CAR_ID in :idCar ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_NOMBRE_CARRERA)
    List<Object[]> consultaCarreraEstudiante(@Param("idCar") List<Integer> idCar);
}
