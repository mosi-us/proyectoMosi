package com.mosi.mosi.bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ETI_Etiquetas", schema = "dbo", catalog = "MOSI")
public class Etiquetas {
    private Integer etiId;
    private Integer pubId;
    private Integer etiIdPersona;
    private Integer etiTipoPersona;
    private Integer etiEstatus;
    private Timestamp etiFechaCreacion;
    private Timestamp etiFechaActualizacion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ETI_Id")
    public Integer getEtiId() {
        return etiId;
    }

    public void setEtiId(Integer etiId) {
        this.etiId = etiId;
    }

    @Basic
    @Column(name = "PUB_Id")
    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    @Basic
    @Column(name = "ETI_IdPersona")
    public Integer getEtiIdPersona() {
        return etiIdPersona;
    }

    public void setEtiIdPersona(Integer etiIdPersona) {
        this.etiIdPersona = etiIdPersona;
    }

    @Basic
    @Column(name = "ETI_TipoPersona")
    public Integer getEtiTipoPersona() {
        return etiTipoPersona;
    }

    public void setEtiTipoPersona(Integer etiTipoPersona) {
        this.etiTipoPersona = etiTipoPersona;
    }

    @Basic
    @Column(name = "ETI_Estatus")
    public Integer getEtiEstatus() {
        return etiEstatus;
    }

    public void setEtiEstatus(Integer etiEstatus) {
        this.etiEstatus = etiEstatus;
    }

    @Basic
    @Column(name = "ETI_FechaCreacion")
    public Timestamp getEtiFechaCreacion() {
        return etiFechaCreacion;
    }

    public void setEtiFechaCreacion(Timestamp etiFechaCreacion) {
        this.etiFechaCreacion = etiFechaCreacion;
    }

    @Basic
    @Column(name = "ETI_FechaActualizacion")
    public Timestamp getEtiFechaActualizacion() {
        return etiFechaActualizacion;
    }

    public void setEtiFechaActualizacion(Timestamp etiFechaActualizacion) {
        this.etiFechaActualizacion = etiFechaActualizacion;
    }

}
