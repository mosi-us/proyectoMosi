package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "HAM_HABILIDADESBLANDASMAESTRO", schema = "dbo", catalog = "MOSI")
public class HabilidadesBlandasMaestro {
    private int hamId;
    private Integer estId;
    private Integer detId;
    private Integer habId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HAM_Id")
    public int getHamId() {
        return hamId;
    }

    public void setHamId(int hamId) {
        this.hamId = hamId;
    }

    @Basic
    @Column(name = "EST_Id")
    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    @Basic
    @Column(name = "DET_Id")
    public Integer getDetId() {
        return detId;
    }

    public void setDetId(Integer detId) {
        this.detId = detId;
    }

    @Basic
    @Column(name = "HAB_Id")
    public Integer getHabId() {
        return habId;
    }
    public void setHabId(Integer habId) {
        this.habId = habId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabilidadesBlandasMaestro that = (HabilidadesBlandasMaestro) o;
        return hamId == that.hamId &&
                Objects.equals(estId, that.estId) &&
                Objects.equals(detId, that.detId)&&
                Objects.equals(habId,that.habId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hamId, estId, detId, habId);
    }
}
