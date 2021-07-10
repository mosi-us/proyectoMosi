package com.mosi.mosi.repository;

import com.mosi.mosi.bean.PublicacionesEntity;
import com.mosi.mosi.bean.Respuestas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<PublicacionesEntity,Integer> {
}
