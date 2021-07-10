package com.mosi.mosi.bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "COM_Comentarios", schema = "dbo", catalog = "MOSI")
public class ComentariosEntity {
    private int comId;
    private Integer pubId;
    private Integer comIdPersona;
    private Integer comTipoPersona;
    private Integer comEstatus;
    private Timestamp comFechaCreacion;
    private Timestamp comFechaActualizacion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COM_Id")
    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    @Basic
    @Column(name = "PUB_id")
    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    @Basic
    @Column(name = "COM_IdPersona")
    public Integer getComIdPersona() {
        return comIdPersona;
    }

    public void setComIdPersona(Integer comIdPersona) {
        this.comIdPersona = comIdPersona;
    }

    @Basic
    @Column(name = "COM_TipoPersona")
    public Integer getComTipoPersona() {
        return comTipoPersona;
    }

    public void setComTipoPersona(Integer comTipoPersona) {
        this.comTipoPersona = comTipoPersona;
    }

    @Basic
    @Column(name = "COM_Estatus")
    public Integer getComEstatus() {
        return comEstatus;
    }

    public void setComEstatus(Integer comEstatus) {
        this.comEstatus = comEstatus;
    }

    @Basic
    @Column(name = "COM_FechaCreacion")
    public Timestamp getComFechaCreacion() {
        return comFechaCreacion;
    }

    public void setComFechaCreacion(Timestamp comFechaCreacion) {
        this.comFechaCreacion = comFechaCreacion;
    }

    @Basic
    @Column(name = "COM_FechaActualizacion")
    public Timestamp getComFechaActualizacion() {
        return comFechaActualizacion;
    }

    public void setComFechaActualizacion(Timestamp comFechaActualizacion) {
        this.comFechaActualizacion = comFechaActualizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComentariosEntity that = (ComentariosEntity) o;

        if (comId != that.comId) return false;
        if (pubId != null ? !pubId.equals(that.pubId) : that.pubId != null) return false;
        if (comIdPersona != null ? !comIdPersona.equals(that.comIdPersona) : that.comIdPersona != null) return false;
        if (comTipoPersona != null ? !comTipoPersona.equals(that.comTipoPersona) : that.comTipoPersona != null)
            return false;
        if (comEstatus != null ? !comEstatus.equals(that.comEstatus) : that.comEstatus != null) return false;
        if (comFechaCreacion != null ? !comFechaCreacion.equals(that.comFechaCreacion) : that.comFechaCreacion != null)
            return false;
        if (comFechaActualizacion != null ? !comFechaActualizacion.equals(that.comFechaActualizacion) : that.comFechaActualizacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = comId;
        result = 31 * result + (pubId != null ? pubId.hashCode() : 0);
        result = 31 * result + (comIdPersona != null ? comIdPersona.hashCode() : 0);
        result = 31 * result + (comTipoPersona != null ? comTipoPersona.hashCode() : 0);
        result = 31 * result + (comEstatus != null ? comEstatus.hashCode() : 0);
        result = 31 * result + (comFechaCreacion != null ? comFechaCreacion.hashCode() : 0);
        result = 31 * result + (comFechaActualizacion != null ? comFechaActualizacion.hashCode() : 0);
        return result;
    }
}
