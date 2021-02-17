package com.mosi.mosi.service;

import com.mosi.mosi.bean.Ciudades;
import com.mosi.mosi.repository.CiudadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ciudadService {
    @Autowired
    CiudadesRepository ciudadesRepository;
    public Ciudades findCiudadById(Integer id) {
        Optional<Ciudades> ciudades = null;
        try {
            ciudades = ciudadesRepository.findById(id);
        } catch (Exception e) {

        }
        return ciudades.get();
    }
}
