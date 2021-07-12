package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentarios,Integer> {
}
