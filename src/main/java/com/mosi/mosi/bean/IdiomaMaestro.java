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

    @Column(name="IDI_Id")
    private Integer idiIdIdioma;

    @Column(name="EST_Id")
    private Integer EstIDIdioma;

    @Column(name="DET_ID")
    private Integer DetIdIdioma;

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

    public Integer getIdiIdIdioma() {
        return idiIdIdioma;
    }

    public void setIdiIdIdioma(Integer idiIdIdioma) {
        this.idiIdIdioma = idiIdIdioma;
    }

    public Integer getEstIDIdioma() {
        return EstIDIdioma;
    }

    public void setEstIDIdioma(Integer estIDIdioma) {
        EstIDIdioma = estIDIdioma;
    }

    public Integer getDetIdIdioma() {
        return DetIdIdioma;
    }

    public void setDetIdIdioma(Integer detIdIdioma) {
        DetIdIdioma = detIdIdioma;
    }
}
