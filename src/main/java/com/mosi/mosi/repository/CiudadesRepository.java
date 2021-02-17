package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Ciudades;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadesRepository extends JpaRepository<Ciudades,Integer> {
    List<Ciudades> findByIdGreaterThan(Integer zero);
}
