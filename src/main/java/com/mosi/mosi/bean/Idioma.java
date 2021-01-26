package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="IDI_IDIOMAS")
public class Idioma implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="IDI_ID")
    private Integer id;

    @Column(name="IDI_Nombre")
    private String NombreIdioma;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreIdioma() {
        return NombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        NombreIdioma = nombreIdioma;
    }
}
