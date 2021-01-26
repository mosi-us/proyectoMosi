package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PAM_PASIONMAESTRO")
public class PasionMaestro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="PAM_Id")
    private Integer id;

    @ApiObjectField(description = "Id de Estudiante", required = true)
    @Column(name="EST_Id")
    private Integer EstId;

    @ApiObjectField(description = "id Detalle de Estudiante (empresas)", required = true)
    @Column(name="DET_Id")
    private Integer DetId;

    @ApiObjectField(description = "Descripcion de Estudiante", required = true)
    @Column(name="PAM_Descripcion")
    private String Descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstId() {
        return EstId;
    }

    public void setEstId(Integer estId) {
        EstId = estId;
    }

    public Integer getDetId() {
        return DetId;
    }

    public void setDetId(Integer detId) {
        DetId = detId;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
