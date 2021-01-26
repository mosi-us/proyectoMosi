package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="DEE_DEPORTEMAESTRO")
public class DeporteMaestro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="DEE_Id")
    private Integer id;

    @Column(name="DEP_Id")
    private Integer depIdDep;

    @Column(name="EST_Id")
    private Integer EstIdDep;

    @Column(name="DET_ID")
    private Integer detIdDep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepIdDeporte() {
        return depIdDep;
    }

    public void setDepIdDeporte(Integer depIdDeporte) {
        this.depIdDep = depIdDeporte;
    }

    public Integer getDepIdDep() {
        return depIdDep;
    }

    public void setDepIdDep(Integer depIdDep) {
        this.depIdDep = depIdDep;
    }

    public Integer getEstIdDep() {
        return EstIdDep;
    }

    public void setEstIdDep(Integer estIdDep) {
        EstIdDep = estIdDep;
    }

    public Integer getDetIdDep() {
        return detIdDep;
    }

    public void setDetIdDep(Integer detIdDep) {
        this.detIdDep = detIdDep;
    }

    public DeporteMaestro() {
    }
}
