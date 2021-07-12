package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PUB_PUBLICACIONES", schema = "dbo", catalog = "MOSI")
public class Publicaciones {
    private int pubId;
    private String pubDescripcion;
    private String pubEnlace;
    private Date pubFechaCreacion;
    private Date pubFechaActualizacion;
    private Date pubFechaInicio;
    private Date pubFechaFin;
    private Integer pubEstatus;
    private Estudiante estudiante;
    private Empresa empresa;
    private Integer tipoPersona;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUB_Id")
    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    @Basic
    @Column(name = "PUB_DESCRIPCION")
    public String getPubDescripcion() {
        return pubDescripcion;
    }

    public void setPubDescripcion(String pubDescripcion) {
        this.pubDescripcion = pubDescripcion;
    }

    @Basic
    @Column(name = "PUB_Enlace")
    public String getPubEnlace() {
        return pubEnlace;
    }

    public void setPubEnlace(String pubEnlace) {
        this.pubEnlace = pubEnlace;
    }

    @Basic
    @Column(name = "PUB_FECHACREACION")
    public Date getPubFechaCreacion() {
        return pubFechaCreacion;
    }

    public void setPubFechaCreacion(Date pubFechaCreacion) {
        this.pubFechaCreacion = pubFechaCreacion;
    }

    @Basic
    @Column(name = "PUB_FECHAACTUALIZACION")
    public Date getPubFechaActualizacion() {
        return pubFechaActualizacion;
    }

    public void setPubFechaActualizacion(Date pubFechaActualizacion) {
        this.pubFechaActualizacion = pubFechaActualizacion;
    }

    @Basic
    @Column(name = "PUB_FECHAINICIO")
    public Date getPubFechaInicio() {
        return pubFechaInicio;
    }

    public void setPubFechaInicio(Date pubFechaInicio) {
        this.pubFechaInicio = pubFechaInicio;
    }

    @Basic
    @Column(name = "PUB_FECHAFIN")
    public Date getPubFechaFin() {
        return pubFechaFin;
    }

    public void setPubFechaFin(Date pubFechaFin) {
        this.pubFechaFin = pubFechaFin;
    }

    @Basic
    @Column(name = "PUB_Estatus")
    public Integer getPubEstatus() {
        return pubEstatus;
    }

    public void setPubEstatus(Integer pubEstatus) {
        this.pubEstatus = pubEstatus;
    }

    @OneToOne
    @JoinColumn(name = "EST_ID")
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    @OneToOne
    @JoinColumn(name = "EMP_ID")
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    @Basic
    @Column(name = "PUB_TIPOPERSONA")

    public Integer getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(Integer tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}
