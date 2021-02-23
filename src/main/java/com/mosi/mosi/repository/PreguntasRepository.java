package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Asignatura;
import com.mosi.mosi.bean.Preguntas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreguntasRepository extends JpaRepository<Preguntas,Integer> {
    List<Preguntas> findByAsignatura(Asignatura asignatura);

    String SQL_COUNT_QUESTION = "select count(PRE_Id) from PRE_Preguntas pre " +
            "inner join DET_Estudiante DE on PRE.ASI_Id = DE.ASI_Id " +
            "where pre.ASI_Id = :idAsi and de.CAR_Id =:carId ";
    @Query(nativeQuery = true, value = SQL_COUNT_QUESTION)
    Integer questionCount(@Param("idAsi") Integer idAsi,@Param("carId") Integer carId);
}
