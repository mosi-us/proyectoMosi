package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "STM_SOFTWARETECNOLOGIASMAESTRO", schema = "dbo", catalog = "MOSI")
public class SoftwareTecnologiasMaestro {
    private int Id;
    private Integer estId;
    private Integer detId;
    private Integer Nivel;
    private Integer sytId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STM_Id")
    public int getId() {
        return Id;
    }

    public void setId(int stmId) {
        this.Id = stmId;
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
    @Column(name = "STM_Nivel")
    public Integer getNivel() {
        return Nivel;
    }

    public void setNivel(Integer stmNivel) {
        this.Nivel = stmNivel;
    }

    @Basic
    @Column(name = "SYT_Id")
    public Integer getIdSyt() {
        return sytId;
    }
    public void setIdSyt(Integer idSyt) {
        this.sytId = idSyt;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareTecnologiasMaestro that = (SoftwareTecnologiasMaestro) o;
        return Id == that.Id &&
                Objects.equals(estId, that.estId) &&
                Objects.equals(detId, that.detId) &&
                Objects.equals(Nivel, that.Nivel) &&
                Objects.equals(sytId,that.sytId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, estId, detId, Nivel, sytId);
    }
}
