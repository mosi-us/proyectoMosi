package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="UNI_UNIVERSIDADES")
public class Universidad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="UNI_Id")
    private Integer id;

    @Column(name="UNI_nombre")
    private String NombreUni;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUni() {
        return NombreUni;
    }

    public void setNombreUni(String nombreCarrera) {
        NombreUni = nombreCarrera;
    }
}
