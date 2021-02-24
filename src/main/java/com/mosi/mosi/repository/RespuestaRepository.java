package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Asignatura;
import com.mosi.mosi.bean.Estudiante;
import com.mosi.mosi.bean.Respuestas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuestas,Integer> {
    List<Respuestas> findByEstudianteAndAsignatura(Estudiante estudiante, Asignatura asignatura);
    List<Respuestas> findByEstudiante(Estudiante estudiante);
}
