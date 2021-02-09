package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CIU_Ciudades", schema = "dbo", catalog = "MOSI")
public class Ciudades {
    private int id;
    private String ciuNombre;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CIU_Id")
    public int getCiuId() {
        return id;
    }

    public void setCiuId(int ciuId) {
        this.id = id;
    }


    @Basic
    @Column(name = "CIU_Nombre")
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
