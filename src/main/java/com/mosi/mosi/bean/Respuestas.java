package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RES_Respuestas", schema = "dbo", catalog = "MOSI")
public class Respuestas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RES_Id")
    private int resId;
    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Basic
    @Column(name = "RES_Respuestas")
    private String resRespuestas;

    @OneToOne
    @JoinColumn(name = "EST_Id")
    Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "ASI_Id")
    Asignatura asignatura;

    @OneToOne
    @JoinColumn(name = "POS_Id")
    Postulaciones postulaciones;

    @OneToOne
    @JoinColumn(name = "PRE_Id")
    Preguntas pregunta;

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public String getResRespuestas() {
        return resRespuestas;
    }
    public void setResRespuestas(String resRespuestas) {
        this.resRespuestas = resRespuestas;
    }

    public Postulaciones getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(Postulaciones postulaciones) {
        this.postulaciones = postulaciones;
    }

    public Preguntas getPregunta() {
        return pregunta;
    }

    public void setPregunta(Preguntas pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Respuestas that = (Respuestas) o;
        return resId == that.resId &&
                Objects.equals(resRespuestas, that.resRespuestas);
    }


    @Override
    public int hashCode() {
        return Objects.hash(resId, resRespuestas);
    }
}
