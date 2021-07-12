package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "REA_Reacciones", schema = "dbo", catalog = "MOSI")
public class Reacciones {
    private int reaId;
    private String reaNombre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REA_Id")
    public int getReaId() {
        return reaId;
    }

    public void setReaId(int reaId) {
        this.reaId = reaId;
    }

    @Basic
    @Column(name = "REA_NOMBRE")
    public String getReaNombre() {
        return reaNombre;
    }

    public void setReaNombre(String reaNombre) {
        this.reaNombre = reaNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reacciones that = (Reacciones) o;
        return reaId == that.reaId &&
                Objects.equals(reaNombre, that.reaNombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reaId, reaNombre);
    }
}
