package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PAI_PAISES")
public class Paises implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiObjectField(description = "Id del pais", required = true)
    @Column(name="PAI_Id")
    private Integer id;

    @ApiObjectField(description = "Nombre del Pais", required = true)
    @Column(name="PAI_Nombre")
    private String nombrePais;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
}
