package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "PBL_PERFILESBLOQUEADOS", schema = "dbo", catalog = "MOSI")
public class PerfilesBloqueados {
    private int pblId;
    private Usuarios usuario;
    private Integer pblIdperfil;
    private Integer pblEstatus;
    private Date pblFechacreacion;
    private Date pblFechamodificacion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PBL_ID")
    public int getPblId() {
        return pblId;
    }

    public void setPblId(int pblId) {
        this.pblId = pblId;
    }



    @OneToOne
    @JoinColumn(name="USU_Id")
    public Usuarios getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    @Basic
    @Column(name = "PBL_IDPERFIL")
    public Integer getPblIdperfil() {
        return pblIdperfil;
    }

    public void setPblIdperfil(Integer pblIdperfil) {
        this.pblIdperfil = pblIdperfil;
    }

    @Basic
    @Column(name = "PBL_ESTATUS")
    public Integer getPblEstatus() {
        return pblEstatus;
    }

    public void setPblEstatus(Integer pblEstatus) {
        this.pblEstatus = pblEstatus;
    }

    @Basic
    @Column(name = "PBL_FECHACREACION")
    public Date getPblFechacreacion() {
        return pblFechacreacion;
    }

    public void setPblFechacreacion(Date pblFechacreacion) {
        this.pblFechacreacion = pblFechacreacion;
    }

    @Basic
    @Column(name = "PBL_FECHAMODIFICACION")
    public Date getPblFechamodificacion() {
        return pblFechamodificacion;
    }

    public void setPblFechamodificacion(Date pblFechamodificacion) {
        this.pblFechamodificacion = pblFechamodificacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerfilesBloqueados that = (PerfilesBloqueados) o;

        if (pblId != that.pblId) return false;
        if (pblIdperfil != null ? !pblIdperfil.equals(that.pblIdperfil) : that.pblIdperfil != null) return false;
        if (pblEstatus != null ? !pblEstatus.equals(that.pblEstatus) : that.pblEstatus != null) return false;
        if (pblFechacreacion != null ? !pblFechacreacion.equals(that.pblFechacreacion) : that.pblFechacreacion != null)
            return false;
        if (pblFechamodificacion != null ? !pblFechamodificacion.equals(that.pblFechamodificacion) : that.pblFechamodificacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pblId;
        result = 31 * result + (pblIdperfil != null ? pblIdperfil.hashCode() : 0);
        result = 31 * result + (pblEstatus != null ? pblEstatus.hashCode() : 0);
        result = 31 * result + (pblFechacreacion != null ? pblFechacreacion.hashCode() : 0);
        result = 31 * result + (pblFechamodificacion != null ? pblFechamodificacion.hashCode() : 0);
        return result;
    }
}
