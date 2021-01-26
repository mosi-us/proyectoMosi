package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ESP_ESPECIALIDAD")
public class Especialidad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiObjectField(description = "Id del Especialidad", required = true)
    @Column(name="ESP_Id")
    private Integer id;

    @ApiObjectField(description = "Nombre de Especialidad", required = true)
    @Column(name="ESP_Nombre")
    private String nombre;

    @ApiObjectField(description = "id de Carrera", required = true)
    @Column(name="CAR_Id")
    private String carId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
