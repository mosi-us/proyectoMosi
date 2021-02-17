package com.mosi.mosi.bean;

import javax.persistence.*;

@Entity
@Table(name = "STM_SOFTWARETECNOLOGIASMAESTRO", schema = "dbo", catalog = "MOSI")
public class SoftwareTecnologiasMaestro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STM_Id")
    private int id;


    @OneToOne
    @JoinColumn(name = "EST_Id")
    private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "DET_Id")
    private DetalleEstudiante detalleEstudiante;


    @Basic
    @Column(name = "STM_Nivel")
    private Integer Nivel;
    public Integer getNivel() {
        return Nivel;
    }
    public void setNivel(Integer stmNivel) {
        this.Nivel = stmNivel;
    }

    @OneToOne
    @JoinColumn(name = "SYT_Id")
    private SoftwareTecnologias softwareTecnologias;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public SoftwareTecnologias getSoftwareTecnologias() {
        return softwareTecnologias;
    }

    public void setSoftwareTecnologias(SoftwareTecnologias softwareTecnologias) {
        this.softwareTecnologias = softwareTecnologias;
    }
}
