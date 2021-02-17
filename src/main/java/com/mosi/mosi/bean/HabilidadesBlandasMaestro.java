package com.mosi.mosi.bean;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HAM_HABILIDADESBLANDASMAESTRO", schema = "dbo", catalog = "MOSI")
public class HabilidadesBlandasMaestro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HAM_Id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "EST_Id")
    private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "DET_Id")
    private DetalleEstudiante detalleEstudiante;

    @OneToOne
    @JoinColumn(name = "HAB_Id")
    private HabilidadesBlandas habilidadesBlandas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public HabilidadesBlandas getHabilidadesBlandas() {
        return habilidadesBlandas;
    }

    public void setHabilidadesBlandas(HabilidadesBlandas habilidadesBlandas) {
        this.habilidadesBlandas = habilidadesBlandas;
    }
}
