package com.mosi.mosi.service;

import com.mosi.mosi.bean.Universidad;
import com.mosi.mosi.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class universidadService {
    @Autowired
    UniversidadRepository universidadRepository;

    public Universidad findUniversidadById(Integer id) {
        Optional<Universidad> universidad = null;
        try {
            universidad = universidadRepository.findById(id);
        } catch (Exception e) {

        }
        return universidad.get();
    }
}
