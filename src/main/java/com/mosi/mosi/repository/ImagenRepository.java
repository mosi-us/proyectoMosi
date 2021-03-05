package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepository extends JpaRepository<Imagen,Integer> {
    Imagen findByUsuIdCreacion(Integer idUser);
}
