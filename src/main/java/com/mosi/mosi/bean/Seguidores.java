package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SEG_Seguidores", schema = "dbo", catalog = "MOSI")
public class Seguidores {
    private int segId;
    private Integer segIdPersona;
    private Integer segIdSeguidor;

    @Id
    @Column(name = "SEG_Id")
    public int getSegId() {
        return segId;
    }

    public void setSegId(int segId) {
        this.segId = segId;
    }

    @Basic
    @Column(name = "SEG_IdPersona")
    public Integer getSegIdPersona() {
        return segIdPersona;
    }

    public void setSegIdPersona(Integer segIdPersona) {
        this.segIdPersona = segIdPersona;
    }

    @Basic
    @Column(name = "SEG_IdSeguidor")
    public Integer getSegIdSeguidor() {
        return segIdSeguidor;
    }

    public void setSegIdSeguidor(Integer segIdSeguidor) {
        this.segIdSeguidor = segIdSeguidor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seguidores that = (Seguidores) o;
        return segId == that.segId &&
                Objects.equals(segIdPersona, that.segIdPersona) &&
                Objects.equals(segIdSeguidor, that.segIdSeguidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segId, segIdPersona, segIdSeguidor);
    }
}
