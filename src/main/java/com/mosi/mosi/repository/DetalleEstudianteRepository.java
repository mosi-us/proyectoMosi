package com.mosi.mosi.repository;

import com.mosi.mosi.bean.DetalleEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleEstudianteRepository extends JpaRepository<DetalleEstudiante,Integer> {

    String SQL_CONSULTA_ESTUDIANTE_EMPRESA = "select * from DET_ESTUDIANTE " +
            "            where ((CAR_Id = :idCar AND PAI_ID=:idPais) OR (ESP_Id=:IdEsp) OR (DET_SEM= :semestre ) AND (ASI_Id=:idAsig))";
    @Query(nativeQuery = true, value = SQL_CONSULTA_ESTUDIANTE_EMPRESA)
    List<DetalleEstudiante> consultar_estudiantes_empresa(@Param("idCar") Integer idsCar,
                                                          @Param("idPais") Integer idPais,
                                                          @Param("IdEsp") Integer idsEsp,
                                                          @Param("semestre") Integer semestre,
                                                          @Param("idAsig") Integer idAsig);
}
