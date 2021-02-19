package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Asignatura;
import com.mosi.mosi.bean.Preguntas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreguntasRepository extends JpaRepository<Preguntas,Integer> {
    List<Preguntas> findByAsignatura(Asignatura asignatura);
}
