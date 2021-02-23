package com.mosi.mosi.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "PER_Permisos", schema = "dbo", catalog = "MOSI")
public class Permisos {
    private int perId;
    private String perDescripcion;
    private Timestamp perFechaCreacion;
    private Timestamp perFechaActualizacion;
    private Timestamp perFechaEliminacion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PER_Id")
    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    @Basic
    @Column(name = "PER_Descripcion")
    public String getPerDescripcion() {
        return perDescripcion;
    }

    public void setPerDescripcion(String perDescripcion) {
        this.perDescripcion = perDescripcion;
    }

    @Basic
    @Column(name = "PER_FECHACREACION")
    public Timestamp getPerFechaCreacion() {
        return perFechaCreacion;
    }

    public void setPerFechaCreacion(Timestamp perFechaCreacion) {
        this.perFechaCreacion = perFechaCreacion;
    }

    @Basic
    @Column(name = "PER_FECHAACTUALIZACION")
    public Timestamp getPerFechaActualizacion() {
        return perFechaActualizacion;
    }

    public void setPerFechaActualizacion(Timestamp perFechaActualizacion) {
        this.perFechaActualizacion = perFechaActualizacion;
    }

    @Basic
    @Column(name = "PER_FECHAELIMINACION")
    public Timestamp getPerFechaEliminacion() {
        return perFechaEliminacion;
    }

    public void setPerFechaEliminacion(Timestamp perFechaEliminacion) {
        this.perFechaEliminacion = perFechaEliminacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permisos permisos = (Permisos) o;
        return perId == permisos.perId &&
                Objects.equals(perDescripcion, permisos.perDescripcion) &&
                Objects.equals(perFechaCreacion, permisos.perFechaCreacion) &&
                Objects.equals(perFechaActualizacion, permisos.perFechaActualizacion) &&
                Objects.equals(perFechaEliminacion, permisos.perFechaEliminacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(perId, perDescripcion, perFechaCreacion, perFechaActualizacion, perFechaEliminacion);
    }
}
