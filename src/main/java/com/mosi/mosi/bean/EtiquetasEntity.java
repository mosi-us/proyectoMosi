package com.mosi.mosi.bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ETI_Etiquetas", schema = "dbo", catalog = "MOSI")
public class EtiquetasEntity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EtiquetasEntity that = (EtiquetasEntity) o;

        if (etiId != null ? !etiId.equals(that.etiId) : that.etiId != null) return false;
        if (pubId != null ? !pubId.equals(that.pubId) : that.pubId != null) return false;
        if (etiIdPersona != null ? !etiIdPersona.equals(that.etiIdPersona) : that.etiIdPersona != null) return false;
        if (etiTipoPersona != null ? !etiTipoPersona.equals(that.etiTipoPersona) : that.etiTipoPersona != null)
            return false;
        if (etiEstatus != null ? !etiEstatus.equals(that.etiEstatus) : that.etiEstatus != null) return false;
        if (etiFechaCreacion != null ? !etiFechaCreacion.equals(that.etiFechaCreacion) : that.etiFechaCreacion != null)
            return false;
        if (etiFechaActualizacion != null ? !etiFechaActualizacion.equals(that.etiFechaActualizacion) : that.etiFechaActualizacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = etiId != null ? etiId.hashCode() : 0;
        result = 31 * result + (pubId != null ? pubId.hashCode() : 0);
        result = 31 * result + (etiIdPersona != null ? etiIdPersona.hashCode() : 0);
        result = 31 * result + (etiTipoPersona != null ? etiTipoPersona.hashCode() : 0);
        result = 31 * result + (etiEstatus != null ? etiEstatus.hashCode() : 0);
        result = 31 * result + (etiFechaCreacion != null ? etiFechaCreacion.hashCode() : 0);
        result = 31 * result + (etiFechaActualizacion != null ? etiFechaActualizacion.hashCode() : 0);
        return result;
    }
}
