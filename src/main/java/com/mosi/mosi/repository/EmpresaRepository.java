package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Empresa;
import com.mosi.mosi.bean.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa,Integer> {

    String SQL_CONSULTA_EMPRESA ="SELECT EMP.*,PAI.PAI_Nombre FROM EMP_Empresa EMP" +
            "inner join PAI_Paises PAI on EMP.PAI_Id = PAI.PAI_Id" +
            "WHERE USU_Id =:idUsu";
    @Query(nativeQuery = true,value = SQL_CONSULTA_EMPRESA)
    Object consultarempresa(@Param("idUsu") Integer idUsu);

    Optional<Empresa> findById(Integer idEmp);
    Empresa findByUsers(Usuarios user);

    String SQL_CONSULTA_EMPRESAS ="SELECT * FROM EMP_Empresa EMP " +
            "WHERE EMP_Id in (:ids) ";
    @Query(nativeQuery = true,value = SQL_CONSULTA_EMPRESAS)
    List<Empresa> consultarEmpresas(@Param("ids") List<Integer> ids );
}
