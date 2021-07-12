package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "REP_Reacciones_personas", schema = "dbo", catalog = "MOSI")
public class ReaccionesPersonas {
    private int repId;
    private Reacciones reacciones;
    private Publicaciones publicacion;
    private PublicacionesCompartidas publicacionesCompartidas;
    private Integer repEstatus;
    private Date repFechaCreacion;
    private Date repFechaActualizacion;
    private Comentarios comentarios;
    private Estudiante estudiante;
    private Empresa empresa;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "REP_Id")
    public int getRepId() {
        return repId;
    }

    public void setRepId(int repId) {
        this.repId = repId;
    }

    @OneToOne
    @JoinColumn(name = "REA_Id")
    public Reacciones getReacciones() {
        return reacciones;
    }

    public void setReacciones(Reacciones reacciones) {
        this.reacciones = reacciones;
    }
    @OneToOne
    @JoinColumn(name = "PUB_Id")
    public Publicaciones getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicaciones publicacion) {
        this.publicacion = publicacion;
    }

    @OneToOne
    @JoinColumn(name = "PUC_Id")

    public PublicacionesCompartidas getPublicacionesCompartidas() {
        return publicacionesCompartidas;
    }

    public void setPublicacionesCompartidas(PublicacionesCompartidas publicacionesCompartidas) {
        this.publicacionesCompartidas = publicacionesCompartidas;
    }
    @Basic
    @Column(name = "REP_Estatus")
    public Integer getRepEstatus() {
        return repEstatus;
    }

    public void setRepEstatus(Integer repEstatus) {
        this.repEstatus = repEstatus;
    }

    @Basic
    @Column(name = "REP_FECHACREACION")
    public Date getRepFechaCreacion() {
        return repFechaCreacion;
    }

    public void setRepFechaCreacion(Date repFechaCreacion) {
        this.repFechaCreacion = repFechaCreacion;
    }

    @Basic
    @Column(name = "REP_FECHAACTUALIZACION")
    public Date getRepFechaActualizacion() {
        return repFechaActualizacion;
    }

    public void setRepFechaActualizacion(Date repFechaActualizacion) {
        this.repFechaActualizacion = repFechaActualizacion;
    }

    @OneToOne
    @JoinColumn(name = "COM_Id")
    public Comentarios getComentarios() {
        return comentarios;
    }

    public void setComentarios(Comentarios comentarios) {
        this.comentarios = comentarios;
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
    @JoinColumn(name = "EMP_Id")
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
  
}
