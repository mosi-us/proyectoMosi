package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EMP_Empresa", schema = "dbo", catalog = "MOSI")
public class Empresa {
    private int empId;
    private String empDescripcion;
    private Integer rubId;
    private String empUbicacion;
    private String empSitioWeb;
    private String empNombre;

    @Id
    @Column(name = "EMP_Id")
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    @Basic
    @Column(name = "EMP_Descripcion")
    public String getEmpDescripcion() {
        return empDescripcion;
    }

    public void setEmpDescripcion(String empDescripcion) {
        this.empDescripcion = empDescripcion;
    }

    @Basic
    @Column(name = "RUB_id")
    public Integer getRubId() {
        return rubId;
    }

    public void setRubId(Integer rubId) {
        this.rubId = rubId;
    }

    @Basic
    @Column(name = "EMP_Ubicacion")
    public String getEmpUbicacion() {
        return empUbicacion;
    }

    public void setEmpUbicacion(String empUbicacion) {
        this.empUbicacion = empUbicacion;
    }

    @Basic
    @Column(name = "EMP_SitioWeb")
    public String getEmpSitioWeb() {
        return empSitioWeb;
    }

    public void setEmpSitioWeb(String empSitioWeb) {
        this.empSitioWeb = empSitioWeb;
    }

    @Basic
    @Column(name = "EMP_Nombre")
    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return empId == empresa.empId &&
                Objects.equals(empDescripcion, empresa.empDescripcion) &&
                Objects.equals(rubId, empresa.rubId) &&
                Objects.equals(empUbicacion, empresa.empUbicacion) &&
                Objects.equals(empSitioWeb, empresa.empSitioWeb) &&
                Objects.equals(empNombre, empresa.empNombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, empDescripcion, rubId, empUbicacion, empSitioWeb, empNombre);
    }
}
