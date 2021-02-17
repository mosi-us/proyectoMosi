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
    @JoinColumn(name = "EMP_Id")
    private  Empresa empresa;

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preguntas preguntas = (Preguntas) o;
        return Id == preguntas.Id &&
                Objects.equals(decripcion, preguntas.decripcion) &&
                Objects.equals(asignatura, preguntas.asignatura) &&
                Objects.equals(empresa, preguntas.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, decripcion, asignatura, empresa);
    }
}
