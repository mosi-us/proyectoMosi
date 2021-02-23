package com.mosi.mosi.bean;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "IMG_Imagen", schema = "dbo", catalog = "MOSI")
public class Imagen {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMG_Id")
    private int imgId;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    @Basic
    @Column(name = "IMG_Ruta")
    private Integer imgRuta;

    public Integer getImgRuta() {
        return imgRuta;
    }

    public void setImgRuta(Integer imgRuta) {
        this.imgRuta = imgRuta;
    }

    @Basic
    @Column(name = "IMG_Estatus")
    private Integer imgEstatus;

    public Integer getImgEstatus() {
        return imgEstatus;
    }

    public void setImgEstatus(Integer imgEstatus) {
        this.imgEstatus = imgEstatus;
    }

    @Basic
    @Column(name = "IMG_Tipo")
    private Integer imgTipo;

    public Integer getImgTipo() {
        return imgTipo;
    }

    public void setImgTipo(Integer imgTipo) {
        this.imgTipo = imgTipo;
    }

    @Basic
    @Column(name = "USU_Id_Creacion")
    private Integer usuIdCreacion;

    public Integer getUsuIdCreacion() {
        return usuIdCreacion;
    }

    public void setUsuIdCreacion(Integer usuIdCreacion) {
        this.usuIdCreacion = usuIdCreacion;
    }

    @Basic
    @Column(name = "IMG_FECHACREACION")
    private Timestamp imgFechaCreacion;

    public Timestamp getImgFechaCreacion() {
        return imgFechaCreacion;
    }

    public void setImgFechaCreacion(Timestamp imgFechaCreacion) {
        this.imgFechaCreacion = imgFechaCreacion;
    }

    @Basic
    @Column(name = "IMG_FECHAACTUALIZACION")
    private Timestamp imgFechaActualizacion;

    public Timestamp getImgFechaActualizacion() {
        return imgFechaActualizacion;
    }

    public void setImgFechaActualizacion(Timestamp imgFechaActualizacion) {
        this.imgFechaActualizacion = imgFechaActualizacion;
    }

    @Basic
    @Column(name = "IMG_FECHAELIMINACION")
    private Timestamp imgFechaEliminacion;
    public Timestamp getImgFechaEliminacion() {
        return imgFechaEliminacion;
    }

    public void setImgFechaEliminacion(Timestamp imgFechaEliminacion) {
        this.imgFechaEliminacion = imgFechaEliminacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagen imagen = (Imagen) o;
        return imgId == imagen.imgId &&
                Objects.equals(imgRuta, imagen.imgRuta) &&
                Objects.equals(imgEstatus, imagen.imgEstatus) &&
                Objects.equals(imgTipo, imagen.imgTipo) &&
                Objects.equals(usuIdCreacion, imagen.usuIdCreacion) &&
                Objects.equals(imgFechaCreacion, imagen.imgFechaCreacion) &&
                Objects.equals(imgFechaActualizacion, imagen.imgFechaActualizacion) &&
                Objects.equals(imgFechaEliminacion, imagen.imgFechaEliminacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgId, imgRuta, imgEstatus, imgTipo, usuIdCreacion, imgFechaCreacion, imgFechaActualizacion, imgFechaEliminacion);
    }
}
