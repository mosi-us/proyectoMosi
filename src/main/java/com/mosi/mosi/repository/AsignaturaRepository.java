package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Asignatura;
import com.mosi.mosi.bean.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura,Integer> {
    String SQL_DETALLE_ASIGNATURA = "select asi.ASI_id," +
            "       asi.ASI_Titulo," +
            "       asi.ASI_Descripcion," +
            "       asi.ASI_Lugar," +
            "       asi.ASI_Tipo," +
            "       emp.EMP_Id," +
            "       emp.EMP_Nombre " +
            "from ASI_Asignatura asi " +
            "inner join EMP_Empresa emp on asi.EMP_Id = emp.EMP_Id " +
            "where asi.ASI_id= :idAsi";
    @Query(nativeQuery = true, value = SQL_DETALLE_ASIGNATURA)
    Object[] consultaDetalleAsignatura(@Param("idAsi") Integer idAsi);

    Asignatura findByAsiId(Integer asiId);

    List<Asignatura> findByEmpresa(Empresa empresa);
}
