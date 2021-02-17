package com.mosi.mosi.repository;

import com.mosi.mosi.bean.Paises;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaisesRepository extends CrudRepository<Paises,Integer> {
    List<Paises> findAllByIdGreaterThan(Integer zero);
    Optional<Paises> findById(Integer id);

}
