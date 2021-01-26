package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PAS_PASION")
public class Pasiones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="PAS_ID")
    private Integer id;

    @Column(name="PAS_Nombre")
    private String nombrePasion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePasion() {
        return nombrePasion;
    }

    public void setNombrePasion(String nombrePasion) {
        this.nombrePasion = nombrePasion;
    }
}
