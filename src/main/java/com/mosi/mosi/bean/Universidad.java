package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="CAR_CARRERASUNIV")
public class Universidad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="CAR_ID")
    private Integer id;

    @Column(name="CAR_NOMBRE")
    private String NombreCarrera;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCarrera() {
        return NombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        NombreCarrera = nombreCarrera;
    }
}
