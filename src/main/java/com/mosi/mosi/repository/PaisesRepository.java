package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Paises;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisesRepository extends JpaRepository<Paises,String> {
    List<Paises> findAllByIdGreaterThan(Integer zero);

}
