package com.mosi.mosi.repository;

import com.mosi.mosi.bean.PasionMaestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PasionesMaestroRepository extends JpaRepository<PasionMaestro,Integer> {
    String SQL_CONSULTA_DESCRIPCION = "SELECT * FROM PAM_PasionMaestro where EST_Id = :estId ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_DESCRIPCION)
    List<Object[]> consultarDescripcion(@Param("estId") Integer estId);
}
