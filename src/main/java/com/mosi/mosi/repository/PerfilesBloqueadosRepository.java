package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.PerfilesBloqueados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PerfilesBloqueadosRepository extends JpaRepository<PerfilesBloqueados,Integer> {

    String SQL_CONSULTA_ESTUDIANTE_ACTIVO = "select TOP 1 * from PBL_PerfilesBloqueados " +
            "where USU_ID = :estId and PBL_IDPERFIL = :idEmp";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_ACTIVO)
    PerfilesBloqueados buscarperfilbloqueado(@Param("estId") Integer usuId,
                           @Param("idEmp") Integer idEmp);

}
