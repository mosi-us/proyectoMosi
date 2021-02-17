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

    @OneToOne
    @JoinColumn(name="CAR_Id")
    private Carrera carrera;

    @OneToOne
    @JoinColumn(name="EST_Id")
    private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name="DET_Id")
    private DetalleEstudiante detalleEstudiante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public DetalleEstudiante getDetalleEstudiante() {
        return detalleEstudiante;
    }

    public void setDetalleEstudiante(DetalleEstudiante detalleEstudiante) {
        this.detalleEstudiante = detalleEstudiante;
    }
}
