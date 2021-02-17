package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="IDE_IDIOMAMAESTRO")
public class IdiomaMaestro implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@ApiObjectField(description = "Id", required = true)
@Column(name="IDE_Id")
private Integer id;

    @OneToOne
    @JoinColumn(name="IDI_Id")
    private Idioma idioma;
    @OneToOne
    @JoinColumn(name="EST_Id")
    private Estudiante estudiante;
    @OneToOne
    @JoinColumn(name="DET_ID")
    private DetalleEstudiante detalleEstudiante;

    @Column(name="IDE_NIVEL")
    private Integer Nivel;

    public Integer getNivel() {
        return Nivel;
    }

    public void setNivel(Integer nivel) {
        Nivel = nivel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
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
