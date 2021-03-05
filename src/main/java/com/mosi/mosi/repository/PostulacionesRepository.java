package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Asignatura;
import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.Postulaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostulacionesRepository extends JpaRepository<Postulaciones,Integer> {
    List<Postulaciones> getByAsignatura(Asignatura asignatura);


    String SQL_UPDATE_SELECCIONAR_ESTUDIANTE = "UPDATE POS_POSTULACIONES SET POS_ESTATUS = :Estatus where EST_Id = :idEstudiante and ASI_ID= :idAsignatura";
    @Transactional
    @Modifying
    @Query(value=SQL_UPDATE_SELECCIONAR_ESTUDIANTE, nativeQuery=true)
    int seleccionarEstudiante(@Param("idEstudiante") int idEstudiante, @Param("idAsignatura") int idAsignatura, @Param("Estatus") int Estatus);

    List<Postulaciones> findByAsignaturaAndPosEstatus(Asignatura asignatura, Integer aceptado);

    Postulaciones findByEstudianteAndAsignatura(Estudiante estudiante, Asignatura asignatura);

    String SQL_UPDATE_RECHAZAR_ESTUDIANTE = "UPDATE POS_POSTULACIONES SET POS_ESTATUS = :Estatus where POS_ID =:idPos ";
    @Transactional
    @Modifying
    @Query(value=SQL_UPDATE_RECHAZAR_ESTUDIANTE, nativeQuery=true)
    Integer rechazarEstudiante(@Param("idPos") Integer portulacion,@Param("Estatus") int Estatus);
}
