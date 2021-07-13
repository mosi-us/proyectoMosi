package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Empresa;
import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.Seguidores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeguidoresRepository extends JpaRepository<Seguidores,Integer> {


    List<Seguidores> findByEstudianteSeguidoAndSegEstatus(Estudiante idSeguido,Integer estatus);
    List<Seguidores> findByEmpresaSeguidoAndAndSegEstatus(Empresa idSeguido, Integer estatus);

    List<Seguidores> findByEstudianteSeguidor(Estudiante idSeguido);
    List<Seguidores> findByEmpresaSeguidor(Empresa idSeguido);

    String SQL_CONSULTA_SEGUIDORES_CANTIDAD_EMP ="SELECT COUNT(SEG_Id) FROM SEG_Seguidores where EMP_Id=:idseg and seg_Estatus = 1";
    @Query(nativeQuery = true,value = SQL_CONSULTA_SEGUIDORES_CANTIDAD_EMP)
    Integer cantidadSeguidoresEmp(@Param("idseg") Integer idUsu);

    String SQL_CONSULTA_SEGUIDORES_CANTIDAD_EST ="SELECT COUNT(SEG_Id) FROM SEG_Seguidores where EST_ID=:idseg and seg_Estatus = 1";
    @Query(nativeQuery = true,value = SQL_CONSULTA_SEGUIDORES_CANTIDAD_EST)
    Integer cantidadSeguidoresEst(@Param("idseg") Integer idUsu);
}
