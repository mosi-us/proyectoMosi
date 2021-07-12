package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COM_Comentarios", schema = "dbo", catalog = "MOSI")
public class Comentarios {
    private int comId;
    private Publicaciones publicacion;
    private PublicacionesCompartidas publicacionesCompartidas;

    private Estudiante estudiante;
    private Empresa empresa;
    private Integer comTipoPersona;
    private Integer comEstatus;
    private Date comFechaCreacion;
    private Date comFechaActualizacion;
    private String descripcionComentario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COM_Id")
    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
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
    @JoinColumn(name = "PUC_Id")
    public PublicacionesCompartidas getPublicacionesCompartidas() {
        return publicacionesCompartidas;
    }

    public void setPublicacionesCompartidas(PublicacionesCompartidas publicacionesCompartidas) {
        this.publicacionesCompartidas = publicacionesCompartidas;
    }


    @OneToOne
    @JoinColumn(name = "EST_Id")
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
    @Column(name = "COM_TIPOPERSONA")
    public Integer getComTipoPersona() {
        return comTipoPersona;
    }

    public void setComTipoPersona(Integer comTipoPersona) {
        this.comTipoPersona = comTipoPersona;
    }

    @Basic
    @Column(name = "COM_Estatus")
    public Integer getComEstatus() {
        return comEstatus;
    }

    public void setComEstatus(Integer comEstatus) {
        this.comEstatus = comEstatus;
    }

    @Basic
    @Column(name = "COM_FECHACREACION")
    public Date getComFechaCreacion() {
        return comFechaCreacion;
    }

    public void setComFechaCreacion(Date comFechaCreacion) {
        this.comFechaCreacion = comFechaCreacion;
    }

    @Basic
    @Column(name = "COM_FECHAACTUALIZACION")
    public Date getComFechaActualizacion() {
        return comFechaActualizacion;
    }

    public void setComFechaActualizacion(Date comFechaActualizacion) {
        this.comFechaActualizacion = comFechaActualizacion;
    }

    @Basic
    @Column(name = "COM_Descripcion")
    public String getDescripcionComentario() {
        return descripcionComentario;
    }

    public void setDescripcionComentario(String descripcionComentario) {
        this.descripcionComentario = descripcionComentario;
    }

}
