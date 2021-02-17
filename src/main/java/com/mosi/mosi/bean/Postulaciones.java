package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "POS_POSTULACIONES", schema = "dbo", catalog = "MOSI")
public class Postulaciones {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POS_Id")
    private int posId;
    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    @OneToOne
    @JoinColumn(name = "ASI_Id")
    private Asignatura asignatura;

    @OneToOne
    @JoinColumn(name = "EMP_Id")
    private Empresa empresa;


    @OneToOne
    @JoinColumn(name = "EST_Id")
    private Estudiante estudiante;


    @OneToOne
    @JoinColumn(name = "DET_Id")
    private DetalleEstudiante detalleEstudiante;

    @Basic
    @Column(name = "POS_Fecha")
    private Date posFecha;
    public Date getPosFecha() {
        return posFecha;
    }

    public void setPosFecha(Date posFecha) {
        this.posFecha = posFecha;
    }

    @Basic
    @Column(name = "POS_Estatus")
    private Integer posEstatus;
    public Integer getPosEstatus() {
        return posEstatus;
    }

    public void setPosEstatus(Integer posEstatus) {
        this.posEstatus = posEstatus;
    }

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

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public DetalleEstudiante getDetalleEstudiante() {
        return detalleEstudiante;
    }

    public void setDetalleEstudiante(DetalleEstudiante detalleEstudiante) {
        this.detalleEstudiante = detalleEstudiante;
    }
}
