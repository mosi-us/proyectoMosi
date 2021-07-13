package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "SEG_Seguidores", schema = "dbo", catalog = "MOSI")
public class Seguidores {
    private int segId;
    private Integer segTipoSeguidor;
    private Integer tipo;
    private Integer segEstatus;
    private Date segFechaCreacion;
    private Date segFechaModificacion;
    private Estudiante estudianteSeguidor;
    private Estudiante estudianteSeguido;
    private Empresa empresaSeguidor;
    private Empresa empresaSeguido;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEG_Id")
    public int getSegId() {
        return segId;
    }

    public void setSegId(int segId) {
        this.segId = segId;
    }

    @Basic
    @Column(name = "SEG_Tipo_Seguidor")
    public Integer getSegTipoSeguidor() {
        return segTipoSeguidor;
    }

    public void setSegTipoSeguidor(Integer segTipoSeguidor) {
        this.segTipoSeguidor = segTipoSeguidor;
    }

    @Basic
    @Column(name = "tipo")
    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "SEG_Estatus")
    public Integer getSegEstatus() {
        return segEstatus;
    }

    public void setSegEstatus(Integer segEstatus) {
        this.segEstatus = segEstatus;
    }

    @Basic
    @Column(name = "SEG_Fecha_Creacion")
    public Date getSegFechaCreacion() {
        return segFechaCreacion;
    }

    public void setSegFechaCreacion(Date segFechaCreacion) {
        this.segFechaCreacion = segFechaCreacion;
    }

    @Basic
    @Column(name = "SEG_Fecha_Modificacion")
    public Date getSegFechaModificacion() {
        return segFechaModificacion;
    }

    public void setSegFechaModificacion(Date segFechaModificacion) {
        this.segFechaModificacion = segFechaModificacion;
    }

    @OneToOne
    @JoinColumn(name = "SEG_Id_Est_Seguidor")

    public Estudiante getEstudianteSeguidor() {
        return estudianteSeguidor;
    }
    public void setEstudianteSeguidor(Estudiante estudianteSeguidor) {
        this.estudianteSeguidor = estudianteSeguidor;
    }

    @OneToOne
    @JoinColumn(name = "SEG_Id_Emp_Seguidor")
    public Empresa getEmpresaSeguidor() {
        return empresaSeguidor;
    }

    public void setEmpresaSeguidor(Empresa empresaSeguidor) {
        this.empresaSeguidor = empresaSeguidor;
    }
    @OneToOne
    @JoinColumn(name = "EST_Id")

    public Estudiante getEstudianteSeguido() {
        return estudianteSeguido;
    }

    public void setEstudianteSeguido(Estudiante estudianteSeguido) {
        this.estudianteSeguido = estudianteSeguido;
    }
    @OneToOne
    @JoinColumn(name = "EMP_Id")
    public Empresa getEmpresaSeguido() {
        return empresaSeguido;
    }

    public void setEmpresaSeguido(Empresa empresaSeguido) {
        this.empresaSeguido = empresaSeguido;
    }
}
