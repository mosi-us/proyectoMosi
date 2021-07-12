package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PUC_Publicaciones_Compartidas", schema = "dbo", catalog = "MOSI")
public class PublicacionesCompartidas {
    private int pucId;
    private Publicaciones publicacion;
    private Estudiante estudiante;
    private Empresa empresa;
    private Integer pucTipoPersona;
    private String pucDescripcion;
    private Date pucFechaCreacion;
    private Date pucFechaActualizacion;
    private Integer pucEstatus;
    private Date pucFechaInicio;
    private Date pucFechaFin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUC_ID")
    public int getPucId() {
        return pucId;
    }

    public void setPucId(int pucId) {
        this.pucId = pucId;
    }

    @OneToOne
    @JoinColumn(name = "PUB_id")
    public Publicaciones getPubId() {
        return publicacion;
    }

    public void setPubId(Publicaciones pubId) {
        this.publicacion = pubId;
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
    @Column(name = "PUC_TIPOPERSONA")
    public Integer getPucTipoPersona() {
        return pucTipoPersona;
    }

    public void setPucTipoPersona(Integer pucTipoPersona) {
        this.pucTipoPersona = pucTipoPersona;
    }

    @Basic
    @Column(name = "PUC_Descripcion")
    public String getPucDescripcion() {
        return pucDescripcion;
    }

    public void setPucDescripcion(String pucDescripcion) {
        this.pucDescripcion = pucDescripcion;
    }

    @Basic
    @Column(name = "PUC_FECHACREACION")
    public Date getPucFechaCreacion() {
        return pucFechaCreacion;
    }

    public void setPucFechaCreacion(Date pucFechaCreacion) {
        this.pucFechaCreacion = pucFechaCreacion;
    }

    @Basic
    @Column(name = "PUC_FECHAACTUALIZACION")
    public Date getPucFechaActualizacion() {
        return pucFechaActualizacion;
    }

    public void setPucFechaActualizacion(Date pucFechaActualizacion) {
        this.pucFechaActualizacion = pucFechaActualizacion;
    }

    @Basic
    @Column(name = "PUC_Estatus")
    public Integer getPucEstatus() {
        return pucEstatus;
    }

    public void setPucEstatus(Integer pucEstatus) {
        this.pucEstatus = pucEstatus;
    }




    @Basic
    @Column(name = "PUC_FECHAINICIO")

    public Date getPucFechaInicio() {
        return pucFechaInicio;
    }

    public void setPucFechaInicio(Date pucFechaInicio) {
        this.pucFechaInicio = pucFechaInicio;
    }
    @Basic
    @Column(name = "PUC_FECHAFIN")
    public Date getPucFechaFin() {
        return pucFechaFin;
    }

    public void setPucFechaFin(Date pucFechaFin) {
        this.pucFechaFin = pucFechaFin;
    }
}
