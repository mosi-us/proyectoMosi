package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversidadRepository extends JpaRepository<Universidad,String> {
    List<Universidad> findAllByIdGreaterThan(Integer zero);

    String SQL_CONSULTA_NOMBRE_UNIVERSIDAD = "SELECT * FROM UNI_Universidades WHERE UNI_ID in :idUni ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_NOMBRE_UNIVERSIDAD)
    List<Object[]> consultaUniversidadEstudiante(@Param("idUni") List<Integer> idUni);
}
