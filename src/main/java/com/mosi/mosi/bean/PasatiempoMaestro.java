package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HOM_PASATIEMPOMAESTRO")
public class PasatiempoMaestro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="HOM_Id")
    private Integer id;

    @OneToOne
    @ApiObjectField(description = "Id de Estudiante", required = true)
    @JoinColumn(name="EST_Id")
    private Estudiante estudiante;

    @OneToOne
    @ApiObjectField(description = "Id de Detalle Estudiante", required = true)
    @JoinColumn(name="DET_Id")
    private DetalleEstudiante detalleEstudiante;

    @OneToOne
    @ApiObjectField(description = "Id de Pasatiempo", required = true)
    @JoinColumn(name="HOB_Id")
    private Pasatiempo pasatiempo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Pasatiempo getPasatiempo() {
        return pasatiempo;
    }

    public void setPasatiempo(Pasatiempo pasatiempo) {
        this.pasatiempo = pasatiempo;
    }
}
