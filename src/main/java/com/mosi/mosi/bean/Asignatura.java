package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ASI_Asignatura", schema = "dbo", catalog = "MOSI")
public class Asignatura {


    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASI_id")
    private Integer asiId;
    public Integer getAsiId() {
        return asiId;
    }
    public void setAsiId(Integer asiId) {
        this.asiId = asiId;
    }

    @Basic
    @Column(name = "ASI_Titulo")
    private String asiTitulo;
    public String getAsiTitulo() {
        return asiTitulo;
    }

    public void setAsiTitulo(String asiTitulo) {
        this.asiTitulo = asiTitulo;
    }

    @Basic
    @Column(name = "ASI_Descripcion")
    private String asiDescripcion;
    public String getAsiDescripcion() {
        return asiDescripcion;
    }

    public void setAsiDescripcion(String asiDescripcion) {
        this.asiDescripcion = asiDescripcion;
    }

    @Basic
    @Column(name = "ASI_Tipo")
    private Integer asiTipo;
    public Integer getAsiTipo() {
        return asiTipo;
    }

    public void setAsiTipo(Integer asiTipo) {
        this.asiTipo = asiTipo;
    }

    @Basic
    @Column(name = "ASI_Lugar")
    private Integer asiLugar;
    public Integer getAsiLugar() {
        return asiLugar;
    }

    public void setAsiLugar(Integer asiLugar) {
        this.asiLugar = asiLugar;
    }

    @OneToOne
    @JoinColumn(name = "EMP_Id")
    private Empresa empresa;

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
        Asignatura that = (Asignatura) o;
        return asiId == that.asiId &&
                Objects.equals(asiTitulo, that.asiTitulo) &&
                Objects.equals(asiDescripcion, that.asiDescripcion) &&
                Objects.equals(asiTipo, that.asiTipo) &&
                Objects.equals(asiLugar, that.asiLugar) &&
                Objects.equals(empresa, that.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asiId, asiTitulo, asiDescripcion, asiTipo, asiLugar, empresa);
    }
}
