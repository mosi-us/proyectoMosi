package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ESE_ESTUDIANTE_ESPECIALIDAD")
public class EstudianteEspecialidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id del ESE", required = true)
    @Column(name="ESE_Id")
    private Integer id;

    @ApiObjectField(description = "id de estudiante", required = true)
    @Column(name="EST_Id")
    private Integer idEst;

    @ApiObjectField(description = "id de Especialidad", required = true)
    @Column(name="ESP_Id")
    private Integer espId;

    @ApiObjectField(description = "id de carrera", required = true)
    @Column(name="CAR_id")
    private Integer carId;

    @ApiObjectField(description = "id de detalle de estudiante", required = true)
    @Column(name="DET_Id")
    private Integer detId;
    public EstudianteEspecialidad(){

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEst() {
        return idEst;
    }

    public void setIdEst(Integer idEst) {
        this.idEst = idEst;
    }

    public Integer getEspId() {
        return espId;
    }

    public void setEspId(Integer espId) {
        this.espId = espId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getDetId() {
        return detId;
    }

    public void setDetId(Integer detId) {
        this.detId = detId;
    }

}
