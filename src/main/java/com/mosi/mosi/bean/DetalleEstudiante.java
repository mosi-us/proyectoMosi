package com.mosi.mosi.bean;

import javax.persistence.*;

@Entity
@Table(name = "DET_Estudiante", schema = "dbo", catalog = "MOSI")
public class DetalleEstudiante {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DET_ID")
    private Integer detId;
    public Integer getDetId() {
        return detId;
    }

    public void setDetId(Integer detId) {
        this.detId = detId;
    }

    @Basic
    @Column(name = "DET_Descripcion")
    private String detDescripcion;
    public String getDetDescripcion() {
        return detDescripcion;
    }

    public void setDetDescripcion(String detDescripcion) {
        this.detDescripcion = detDescripcion;
    }

    @OneToOne
    @JoinColumn(name = "ASI_Id")
    private Asignatura asignatura;

    @OneToOne
    @JoinColumn(name = "EMP_ID")
    private Empresa empresa;

    @OneToOne
    @JoinColumn(name = "PAI_ID")
    private Paises pais;

    @Basic
    @Column(name = "DET_SEM")
    private Integer detSem;

    public Integer getDetSem() {
        return detSem;
    }
    public void setDetSem(Integer detSem) {
        this.detSem = detSem;
    }

    @OneToOne
    @JoinColumn(name = "CAR_Id")
    private Carrera carrera;

    @OneToOne
    @JoinColumn(name = "UNI_Id")
    private Universidad universidad;

    @Basic
    @Column(name = "DET_TIPO")
    private Integer tipo;

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

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
