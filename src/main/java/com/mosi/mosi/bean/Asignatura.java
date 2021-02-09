package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ASI_Asignatura", schema = "dbo", catalog = "MOSI")
public class Asignatura {
    private Integer asiId;
    private String asiTitulo;
    private String asiDescripcion;
    private String asiTipo;
    private String asiLugar;
    private Integer empId;

    @Id
    @Column(name = "ASI_id")
    public int getAsiId() {
        return asiId;
    }

    public void setAsiId(int asiId) {
        this.asiId = asiId;
    }

    @Basic
    @Column(name = "ASI_Titulo")
    public String getAsiTitulo() {
        return asiTitulo;
    }

    public void setAsiTitulo(String asiTitulo) {
        this.asiTitulo = asiTitulo;
    }

    @Basic
    @Column(name = "ASI_Descripcion")
    public String getAsiDescripcion() {
        return asiDescripcion;
    }

    public void setAsiDescripcion(String asiDescripcion) {
        this.asiDescripcion = asiDescripcion;
    }

    @Basic
    @Column(name = "ASI_Tipo")
    public String getAsiTipo() {
        return asiTipo;
    }

    public void setAsiTipo(String asiTipo) {
        this.asiTipo = asiTipo;
    }

    @Basic
    @Column(name = "ASI_Lugar")
    public String getAsiLugar() {
        return asiLugar;
    }

    public void setAsiLugar(String asiLugar) {
        this.asiLugar = asiLugar;
    }

    @Basic
    @Column(name = "EMP_Id")
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
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
                Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asiId, asiTitulo, asiDescripcion, asiTipo, asiLugar, empId);
    }
}
