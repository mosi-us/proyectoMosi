package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Empresa;
import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.Seguidores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeguidoresRepository extends JpaRepository<Seguidores,Integer> {

    String SQL_CONSULTA_ESTUDIANTE_SEGUIDO_ESTUDIANTE_SEGUIDOR =" select * from SEG_Seguidores where EST_id=:seguido and SEG_Id_Est_Seguidor=:seguidor";
    @Query(nativeQuery = true,value = SQL_CONSULTA_ESTUDIANTE_SEGUIDO_ESTUDIANTE_SEGUIDOR)
    Seguidores findByEstudianteSeguidoAndEstudianteSeguidor(@Param("seguido") Integer estSeguido,@Param("seguidor") Integer estSeguidor);

    String SQL_CONSULTA_ESTUDIANTE_SEGUIDO_EMPRESA_SEGUIDOR = "select * from SEG_Seguidores where EST_id=:seguido and SEG_Id_Emp_Seguidor=:seguidor";
    @Query(nativeQuery = true,value = SQL_CONSULTA_ESTUDIANTE_SEGUIDO_EMPRESA_SEGUIDOR)
    Seguidores findByEstudianteSeguidoAndEmpresaSeguidor(@Param("seguido") Integer estSeguido,@Param("seguidor") Integer estSeguidor);

    String SQL_CONSULTA_EMPRESA_SEGUIDO_ESTUDIANTE_SEGUIDOR ="select * from SEG_Seguidores where EMP_Id=:seguido and SEG_Id_Est_Seguidor=:seguidor";

    @Query(nativeQuery = true,value = SQL_CONSULTA_EMPRESA_SEGUIDO_ESTUDIANTE_SEGUIDOR)
    Seguidores findByEmpresaSeguidoAndEstudianteSeguidor(@Param("seguido") Integer estSeguido, @Param("seguidor")Integer estSeguidor);

    String SQL_CONSULTA_EMPRESA_SEGUIDO_EMPRESA_SEGUIDOR ="select * from SEG_Seguidores where  EMP_Id=:seguido and SEG_Id_Emp_Seguidor=:seguidor";
    @Query(nativeQuery = true,value = SQL_CONSULTA_EMPRESA_SEGUIDO_EMPRESA_SEGUIDOR)
    Seguidores findByEmpresaSeguidoAndEmpresaSeguidor(@Param("seguido") Integer estSeguido, @Param("seguidor") Integer estSeguidor);

    List<Seguidores> findByEstudianteSeguidoAndSegEstatus(Estudiante idSeguido,Integer estatus);
    List<Seguidores> findByEmpresaSeguidoAndAndSegEstatus(Empresa idSeguido, Integer estatus);

    List<Seguidores> findByEstudianteSeguidorAndSegEstatus(Estudiante idSeguido,Integer estatus);
    List<Seguidores> findByEmpresaSeguidorAndSegEstatus(Empresa idSeguido,Integer estatus);

    String SQL_CONSULTA_SEGUIDORES_CANTIDAD_EMP ="SELECT COUNT(SEG_Id) FROM SEG_Seguidores where EMP_Id=:idseg and seg_Estatus = 1";
    @Query(nativeQuery = true,value = SQL_CONSULTA_SEGUIDORES_CANTIDAD_EMP)
    Integer cantidadSeguidoresEmp(@Param("idseg") Integer idUsu);

    String SQL_CONSULTA_SEGUIDORES_CANTIDAD_EST ="SELECT COUNT(SEG_Id) FROM SEG_Seguidores where EST_ID=:idseg and seg_Estatus = 1";
    @Query(nativeQuery = true,value = SQL_CONSULTA_SEGUIDORES_CANTIDAD_EST)
    Integer cantidadSeguidoresEst(@Param("idseg") Integer idUsu);
}
