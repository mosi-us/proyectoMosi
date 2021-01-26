package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="UNE_UNIVERSIDADESTUDIANTE")
public class UniversidadEstudiante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id del Estudiante", required = true)
    @Column(name="UNE_Id")
    private Integer id;

    @Column(name="UNI_Id")
    private Integer uniIdUniver;

    @Column(name="EST_Id")
    private Integer estIdUniversidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUniIdUniver() {
        return uniIdUniver;
    }

    public void setUniIdUniver(Integer uniIdUniver) {
        this.uniIdUniver = uniIdUniver;
    }

    public Integer getEstIdUniversidad() {
        return estIdUniversidad;
    }

    public void setEstIdUniversidad(Integer estIdUniversidad) {
        this.estIdUniversidad = estIdUniversidad;
    }
}
