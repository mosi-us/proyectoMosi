package com.mosi.mosi.repository;

import com.mosi.mosi.bean.DetalleEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleEstudianteRepository extends JpaRepository<DetalleEstudiante,Integer> {

    String SQL_CONSULTA_ESTUDIANTE_EMPRESA = "select * from DET_ESTUDIANTE " +
            "            where ((CAR_Id = :idCar AND PAI_ID=:idPais) OR (DET_SEM= :semestre ) AND (ASI_Id=:idAsig))";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_EMPRESA)
    List<DetalleEstudiante> consultar_estudiantes_empresa(@Param("idCar") Integer idsCar,
                                                          @Param("idPais") Integer idPais,
                                                          @Param("semestre") Integer semestre,
                                                          @Param("idAsig") Integer idAsig);

    String SQL_CONSULTAR_LUGAR_TRABAJO= "select ASI.ASI_Lugar from ASI_Asignatura ASI " +
            "INNER JOIN DET_Estudiante DE on ASI.ASI_id = DE.ASI_Id WHERE ASI.EMP_Id =:empId";
    @Query(nativeQuery = true,value = SQL_CONSULTAR_LUGAR_TRABAJO)
    Integer consultaLugarTrabajo (@Param("empId") Integer empId);
}
