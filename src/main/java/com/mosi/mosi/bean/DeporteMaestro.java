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

    @OneToOne
    @JoinColumn(name="DEP_Id")
    private Deporte deporte;


    @OneToOne
    @JoinColumn(name="EST_Id")
    private Estudiante estudiante;


    @OneToOne
    @JoinColumn(name="DET_ID")
    private DetalleEstudiante detalleEstudiante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Deporte getDeporte() {
        return deporte;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
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

    public DeporteMaestro() {
    }
}
