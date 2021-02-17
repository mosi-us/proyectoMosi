package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "HAB_HABILIDADESBLANDAS", schema = "dbo", catalog = "MOSI")
public class HabilidadesBlandas {
    private Integer habId;
    private String habNombre;

    @Id
    @Column(name = "HAB_id")
    public Integer getHabId() {
        return habId;
    }

    public void setHabId(int habId) {
        this.habId = habId;
    }

    @Basic
    @Column(name = "HAB_Nombre")
    public String getHabNombre() {
        return habNombre;
    }

    public void setHabNombre(String habNombre) {
        this.habNombre = habNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabilidadesBlandas that = (HabilidadesBlandas) o;
        return habId == that.habId &&
                Objects.equals(habNombre, that.habNombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habId, habNombre);
    }
}
