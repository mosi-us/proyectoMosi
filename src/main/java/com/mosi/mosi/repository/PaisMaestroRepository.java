package com.mosi.mosi.repository;

import com.mosi.mosi.bean.DetalleEstudiante;
import com.mosi.mosi.bean.PaisMaestro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisMaestroRepository extends JpaRepository<PaisMaestro,Integer> {
    List<PaisMaestro> findByDetalleEstudiante(DetalleEstudiante detalleEstudiante);
}
