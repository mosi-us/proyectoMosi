package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IdiomaRepository extends JpaRepository<Idioma,Integer> {

    String SQL_CONSULTA_NOMBRE_IDIOMA_POR_NOMBRE = "SELECT * FROM IDI_Idiomas order by idi_nombre";
    @Query(nativeQuery = true, value = SQL_CONSULTA_NOMBRE_IDIOMA_POR_NOMBRE)
    List<Idioma> consutarIdiomasPorNombre();

    String SQL_CONSULTA_NOMBRE_IDIOMA = "SELECT * FROM IDI_Idiomas WHERE IDI_ID in :idiIdi ";
    @Query(nativeQuery = true, value = SQL_CONSULTA_NOMBRE_IDIOMA)
    List<Object[]> consultaIdiomaEstudiante(@Param("idiIdi") List<Object[]> idiIdi);
}
