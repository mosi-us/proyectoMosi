package com.mosi.mosi.repository;

import com.mosi.mosi.bean.SoftwareTecnologias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoftwareTecnologiaRepository extends JpaRepository<SoftwareTecnologias,Integer> {
    List<SoftwareTecnologias> findAllBySytIdGreaterThan(Integer zero);
}
