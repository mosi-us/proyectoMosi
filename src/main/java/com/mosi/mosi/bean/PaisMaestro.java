package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PAM_PAISMAESTRO", schema = "dbo", catalog = "MOSI")
public class PaisMaestro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAM_Id")
    private int pamId;

    @OneToOne
    @JoinColumn(name = "PAI_Id")
    private Paises paises;


    @OneToOne
    @JoinColumn(name = "DET_Id")
    private DetalleEstudiante detalleEstudiante;

    public int getPamId() {
        return pamId;
    }
    public void setPamId(int pamId) {
        this.pamId = pamId;
    }

    public Paises getPaises() {
        return paises;
    }

    public void setPaises(Paises paises) {
        this.paises = paises;
    }

    private DetalleEstudiante getDetalleEstudiante() {
        return detalleEstudiante;
    }

    public void setDetalleEstudiante(DetalleEstudiante detalleEstudiante) {
        this.detalleEstudiante = detalleEstudiante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaisMaestro that = (PaisMaestro) o;
        return pamId == that.pamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pamId);
    }
}
