package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PRE_Preguntas", schema = "dbo", catalog = "MOSI")
public class Preguntas {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRE_Id")
    private int Id;
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Basic
    @Column(name = "PRE_Decripcion")
    private String decripcion;
    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }

    @OneToOne
    @JoinColumn(name = "ASI_Id")
    private Asignatura asignatura;

    @OneToOne
    @JoinColumn(name = "DET_Id")
    private  DetalleEstudiante detalleEstudiante;

    private Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    private DetalleEstudiante getDetalleEstudiante() {
        return detalleEstudiante;
    }

    public void setDetalleEstudiante(DetalleEstudiante detalleEstudiante) {
        this.detalleEstudiante = detalleEstudiante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preguntas preguntas = (Preguntas) o;
        return Id == preguntas.Id &&
                Objects.equals(decripcion, preguntas.decripcion) &&
                Objects.equals(asignatura, preguntas.asignatura) &&
                Objects.equals(detalleEstudiante, preguntas.detalleEstudiante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, decripcion, asignatura, detalleEstudiante);
    }
}
