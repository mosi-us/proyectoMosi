package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CIU_Ciudades", schema = "dbo", catalog = "MOSI")
public class Ciudades {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CIU_Id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CIU_Nombre")
    private String ciuNombre;
    public String getCiuNombre() {
        return ciuNombre;
    }

    public void setCiuNombre(String ciuNombre) {
        this.ciuNombre = ciuNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudades that = (Ciudades) o;
        return id == that.id &&
                Objects.equals(ciuNombre, that.ciuNombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ciuNombre);
    }
}
