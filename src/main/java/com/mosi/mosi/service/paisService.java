package com.mosi.mosi.service;

import com.mosi.mosi.bean.Paises;
import com.mosi.mosi.repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class paisService {

    @Autowired
    PaisesRepository paisesRepository;

    public  Paises findPaisbyId(Integer id) {
        Optional<Paises> pais = null;
        try {
            pais = paisesRepository.findById(id);
        } catch (Exception e) {

        }
        return pais.get();
    }
}
