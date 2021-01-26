package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="CAM_CARRERASUNIVMAESTRO")
public class CarreraUniversitariaMaestro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="CAM_Id")
    private Integer id;

    @Column(name="CAM_Semestre")
    private Integer semestre;

    @Column(name="CAR_Id")
    private Integer caridCar;

    @Column(name="EST_Id")
    private Integer estIdCar;

    @Column(name="DET_Id")
    private Integer detIdCar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstIdCar() {
        return estIdCar;
    }

    public void setEstIdCar(Integer estIdCar) {
        this.estIdCar = estIdCar;
    }

    public Integer getDetIdCar() {
        return detIdCar;
    }

    public void setDetIdCar(Integer detIdCar) {
        this.detIdCar = detIdCar;
    }

    public Integer getCaridCar() {
        return caridCar;
    }

    public void setCaridCar(Integer caridCar) {
        this.caridCar = caridCar;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }
}
