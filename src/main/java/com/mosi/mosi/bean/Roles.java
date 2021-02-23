package com.mosi.mosi.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ROL_Roles", schema = "dbo", catalog = "MOSI")
public class Roles {
    private int rolId;
    private Integer rolEstatus;
    private Timestamp rolFechaCreacion;
    private Timestamp rolFechaActualizacion;
    private Timestamp rolFechaEliminacion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_Id")
    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    @Basic
    @Column(name = "ROL_Estatus")
    public Integer getRolEstatus() {
        return rolEstatus;
    }

    public void setRolEstatus(Integer rolEstatus) {
        this.rolEstatus = rolEstatus;
    }

    @Basic
    @Column(name = "ROL_FECHACREACION")
    public Timestamp getRolFechaCreacion() {
        return rolFechaCreacion;
    }

    public void setRolFechaCreacion(Timestamp rolFechaCreacion) {
        this.rolFechaCreacion = rolFechaCreacion;
    }

    @Basic
    @Column(name = "ROL_FECHAACTUALIZACION")
    public Timestamp getRolFechaActualizacion() {
        return rolFechaActualizacion;
    }

    public void setRolFechaActualizacion(Timestamp rolFechaActualizacion) {
        this.rolFechaActualizacion = rolFechaActualizacion;
    }

    @Basic
    @Column(name = "ROL_FECHAELIMINACION")
    public Timestamp getRolFechaEliminacion() {
        return rolFechaEliminacion;
    }

    public void setRolFechaEliminacion(Timestamp rolFechaEliminacion) {
        this.rolFechaEliminacion = rolFechaEliminacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles rolRoles = (Roles) o;
        return rolId == rolRoles.rolId &&
                Objects.equals(rolEstatus, rolRoles.rolEstatus) &&
                Objects.equals(rolFechaCreacion, rolRoles.rolFechaCreacion) &&
                Objects.equals(rolFechaActualizacion, rolRoles.rolFechaActualizacion) &&
                Objects.equals(rolFechaEliminacion, rolRoles.rolFechaEliminacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolId, rolEstatus, rolFechaCreacion, rolFechaActualizacion, rolFechaEliminacion);
    }
}
