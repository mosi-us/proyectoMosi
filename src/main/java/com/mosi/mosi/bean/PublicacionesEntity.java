package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PUB_PUBLICACIONES", schema = "dbo", catalog = "MOSI")
public class PublicacionesEntity {
    private int pubId;
    private String pubDescripcion;
    private String pubEnlace;
    private Date pubFechaCreacion;
    private Date pubFechaActualizacion;
    private Date pubFechaInicio;
    private Date pubFechaFin;
    private Integer pubEstatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUB_Id")
    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    @Basic
    @Column(name = "PUB_DESCRIPCION")
    public String getPubDescripcion() {
        return pubDescripcion;
    }

    public void setPubDescripcion(String pubDescripcion) {
        this.pubDescripcion = pubDescripcion;
    }

    @Basic
    @Column(name = "PUB_Enlace")
    public String getPubEnlace() {
        return pubEnlace;
    }

    public void setPubEnlace(String pubEnlace) {
        this.pubEnlace = pubEnlace;
    }

    @Basic
    @Column(name = "PUB_FECHACREACION")
    public Date getPubFechaCreacion() {
        return pubFechaCreacion;
    }

    public void setPubFechaCreacion(Date pubFechaCreacion) {
        this.pubFechaCreacion = pubFechaCreacion;
    }

    @Basic
    @Column(name = "PUB_FECHAACTUALIZACION")
    public Date getPubFechaActualizacion() {
        return pubFechaActualizacion;
    }

    public void setPubFechaActualizacion(Date pubFechaActualizacion) {
        this.pubFechaActualizacion = pubFechaActualizacion;
    }

    @Basic
    @Column(name = "PUB_FECHAINICIO")
    public Date getPubFechaInicio() {
        return pubFechaInicio;
    }

    public void setPubFechaInicio(Date pubFechaInicio) {
        this.pubFechaInicio = pubFechaInicio;
    }

    @Basic
    @Column(name = "PUB_FECHAFIN")
    public Date getPubFechaFin() {
        return pubFechaFin;
    }

    public void setPubFechaFin(Date pubFechaFin) {
        this.pubFechaFin = pubFechaFin;
    }

    @Basic
    @Column(name = "PUB_Estatus")
    public Integer getPubEstatus() {
        return pubEstatus;
    }

    public void setPubEstatus(Integer pubEstatus) {
        this.pubEstatus = pubEstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicacionesEntity that = (PublicacionesEntity) o;

        if (pubId != that.pubId) return false;
        if (pubDescripcion != null ? !pubDescripcion.equals(that.pubDescripcion) : that.pubDescripcion != null)
            return false;
        if (pubEnlace != null ? !pubEnlace.equals(that.pubEnlace) : that.pubEnlace != null) return false;
        if (pubFechaCreacion != null ? !pubFechaCreacion.equals(that.pubFechaCreacion) : that.pubFechaCreacion != null)
            return false;
        if (pubFechaActualizacion != null ? !pubFechaActualizacion.equals(that.pubFechaActualizacion) : that.pubFechaActualizacion != null)
            return false;
        if (pubFechaInicio != null ? !pubFechaInicio.equals(that.pubFechaInicio) : that.pubFechaInicio != null)
            return false;
        if (pubFechaFin != null ? !pubFechaFin.equals(that.pubFechaFin) : that.pubFechaFin != null) return false;
        if (pubEstatus != null ? !pubEstatus.equals(that.pubEstatus) : that.pubEstatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pubId;
        result = 31 * result + (pubDescripcion != null ? pubDescripcion.hashCode() : 0);
        result = 31 * result + (pubEnlace != null ? pubEnlace.hashCode() : 0);
        result = 31 * result + (pubFechaCreacion != null ? pubFechaCreacion.hashCode() : 0);
        result = 31 * result + (pubFechaActualizacion != null ? pubFechaActualizacion.hashCode() : 0);
        result = 31 * result + (pubFechaInicio != null ? pubFechaInicio.hashCode() : 0);
        result = 31 * result + (pubFechaFin != null ? pubFechaFin.hashCode() : 0);
        result = 31 * result + (pubEstatus != null ? pubEstatus.hashCode() : 0);
        return result;
    }
}
