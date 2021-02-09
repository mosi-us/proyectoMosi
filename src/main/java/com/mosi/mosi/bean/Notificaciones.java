package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "NOT_NOTIFICACIONES", schema = "dbo", catalog = "MOSI")
public class Notificaciones {
    private int id;
    private String notTitulo;
    private String notDetalle;
    private Integer notIdRemitente;
    private Integer notIdDestino;
    private Integer notEstatus;
    private Date notFechaEnvio;
    private Date notFechaRecibido;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOT_ID")
    public int getId() {
        return id;
    }

    public void setId(int notId) {
        this.id = notId;
    }

    @Basic
    @Column(name = "NOT_Titulo")
    public String getNotTitulo() {
        return notTitulo;
    }

    public void setNotTitulo(String notTitulo) {
        this.notTitulo = notTitulo;
    }

    @Basic
    @Column(name = "NOT_Detalle")
    public String getNotDetalle() {
        return notDetalle;
    }

    public void setNotDetalle(String notDetalle) {
        this.notDetalle = notDetalle;
    }

    @Basic
    @Column(name = "NOT_idremitente")
    public Integer getNotIdRemitente() {
        return notIdRemitente;
    }

    public void setNotIdRemitente(Integer notIdRemitente) {
        this.notIdRemitente = notIdRemitente;
    }

    @Basic
    @Column(name = "NOT_iddestino")
    public Integer getNotIdDestino() {
        return notIdDestino;
    }

    public void setNotIdDestino(Integer notIdDestino) {
        this.notIdDestino = notIdDestino;
    }

    @Basic
    @Column(name = "NOT_Estatus")
    public Integer getNotEstatus() {
        return notEstatus;
    }

    public void setNotEstatus(Integer notEstatus) {
        this.notEstatus = notEstatus;
    }

    @Basic
    @Column(name = "NOT_fechaenvio")
    public Date getNotFechaEnvio() {
        return notFechaEnvio;
    }

    public void setNotFechaEnvio(Date notFechaEnvio) {
        this.notFechaEnvio = notFechaEnvio;
    }

    @Basic
    @Column(name = "NOT_fecharecibido")
    public Date getNotFechaRecibido() {
        return notFechaRecibido;
    }

    public void setNotFechaRecibido(Date notFechaRecibido) {
        this.notFechaRecibido = notFechaRecibido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notificaciones that = (Notificaciones) o;
        return id == that.id &&
                Objects.equals(notTitulo, that.notTitulo) &&
                Objects.equals(notDetalle, that.notDetalle) &&
                Objects.equals(notIdRemitente, that.notIdRemitente) &&
                Objects.equals(notIdDestino, that.notIdDestino) &&
                Objects.equals(notEstatus, that.notEstatus) &&
                Objects.equals(notFechaEnvio, that.notFechaEnvio) &&
                Objects.equals(notFechaRecibido, that.notFechaRecibido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notTitulo, notDetalle, notIdRemitente, notIdDestino, notEstatus, notFechaEnvio, notFechaRecibido);
    }
}
