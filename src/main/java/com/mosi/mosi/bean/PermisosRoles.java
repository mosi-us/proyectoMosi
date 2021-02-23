package com.mosi.mosi.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PRO_PERMISOSROLES", schema = "dbo", catalog = "MOSI")
public class PermisosRoles {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_Id")
    private int perId;
    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    @Basic
    @Column(name = "PRO_Estatus")
    private Integer proEstatus;
    public Integer getProEstatus() {
        return proEstatus;
    }

    public void setProEstatus(Integer proEstatus) {
        this.proEstatus = proEstatus;
    }

    @Basic
    @Column(name = "PRO_FechaCreacion")
    private Timestamp proFechaCreacion;

    public Timestamp getProFechaCreacion() {
        return proFechaCreacion;
    }

    public void setProFechaCreacion(Timestamp proFechaCreacion) {
        this.proFechaCreacion = proFechaCreacion;
    }

    @Basic
    @Column(name = "PRO_FechaActualizacion")
    private Timestamp proFechaActualizacion;
    public Timestamp getProFechaActualizacion() {
        return proFechaActualizacion;
    }

    public void setProFechaActualizacion(Timestamp proFechaActualizacion) {
        this.proFechaActualizacion = proFechaActualizacion;
    }

    @Basic
    @Column(name = "PRO_FechaEliminacion")
    private Timestamp proFechaEliminacion;
    public Timestamp getProFechaEliminacion() {
        return proFechaEliminacion;
    }

    public void setProFechaEliminacion(Timestamp proFechaEliminacion) {
        this.proFechaEliminacion = proFechaEliminacion;
    }
    @OneToOne
    @JoinColumn(name = "PER_Id")
    private Permisos permisos;

    @OneToOne
    @JoinColumn(name = "ROL_Id")
    private Roles roles;

    public Permisos getPermisos() {
        return permisos;
    }

    public void setPermisos(Permisos permisos) {
        this.permisos = permisos;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermisosRoles that = (PermisosRoles) o;
        return perId == that.perId &&
                Objects.equals(proEstatus, that.proEstatus) &&
                Objects.equals(proFechaCreacion, that.proFechaCreacion) &&
                Objects.equals(proFechaActualizacion, that.proFechaActualizacion) &&
                Objects.equals(proFechaEliminacion, that.proFechaEliminacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perId, proEstatus, proFechaCreacion, proFechaActualizacion, proFechaEliminacion);
    }
}
