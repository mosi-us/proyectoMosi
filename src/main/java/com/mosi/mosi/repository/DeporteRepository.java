package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Deporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeporteRepository extends JpaRepository<Deporte,String> {
    List<Deporte> findAllByIdGreaterThan(Integer zero);

    String SQL_CONSULTA_NOMBRE_DEPORTE = "SELECT * FROM DEP_Deportes WHERE DEP_ID in (:idDep) ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_NOMBRE_DEPORTE)
    List<Object[]> consultaDeporteEstudiante(@Param("idDep") List<Integer> idDep);
}
