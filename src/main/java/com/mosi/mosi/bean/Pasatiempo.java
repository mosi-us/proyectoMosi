package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="HOB_PASATIEMPO")
public class Pasatiempo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="HOB_Id")
    private Integer id;

    @Column(name="HOB_Nombre")
    private String NombrePasatiempo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePasatiempo() {
        return NombrePasatiempo;
    }

    public void setNombrePasatiempo(String nombrePasatiempo) {
        NombrePasatiempo = nombrePasatiempo;
    }
}
