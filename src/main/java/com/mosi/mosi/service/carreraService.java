package com.mosi.mosi.service;

import com.mosi.mosi.bean.Carrera;
import com.mosi.mosi.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class carreraService {
    @Autowired
    CarreraRepository carreraRepository;

    public Carrera findCarreraById(Integer id) {
        Optional<com.mosi.mosi.bean.Carrera> carrera = null;
        try {
            carrera = carreraRepository.findById(id);
        } catch (Exception e) {

        }
        return carrera.get();
    }
}
