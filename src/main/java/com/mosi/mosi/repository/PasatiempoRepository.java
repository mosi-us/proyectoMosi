package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Pasatiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasatiempoRepository extends JpaRepository<Pasatiempo,String> {
    List<Pasatiempo> findAllByIdGreaterThan(Integer zero);

    String SQL_CONSULTA_NOMBRE_PASATIEMPO = "SELECT * FROM HOB_Pasatiempo WHERE HOB_ID in :pasId ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_NOMBRE_PASATIEMPO)
    List<Object[]> consultaPasatiempoEstudiante(@Param("pasId") List<Integer> pasId);
}
