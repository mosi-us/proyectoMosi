package com.mosi.mosi.repository;

import com.mosi.mosi.bean.HabilidadesBlandas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabilidadesBlandasRepository extends JpaRepository<HabilidadesBlandas,Integer> {
    List<HabilidadesBlandas> findAllByHabIdGreaterThan(Integer zero);


}
