package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "DET_Estudiante")
public class DetalleEstudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id del Estudiante", required = true)
    @Column(name="DET_id")
    private Integer id;

    @ApiObjectField(description = "Nombre de Estudiante", required = true)
    @Column(name="DET_Descripcion")
    private String descripcion;

    @ApiObjectField(description = "id del Especialidad", required = true)
    @Column(name="ESP_Id")
    private Integer DetIdEsp;


    @ApiObjectField(description = "Apellido del estudiante", required = true)
    @Column(name="ASI_Id")
    private String Asignatura;

    @ApiObjectField(description = "Fecha de Nacimiento", required = true)
    @Column(name="EMP_Id")
    private Integer idEmp;

    @ApiObjectField(description = "id del Pais", required = true)
    @Column(name="PAI_Id")
    private Integer idpai;

    @ApiObjectField(description = "Semestre", required = true)
    @Column(name="DET_Sem")
    private Integer semestre;

    @ApiObjectField(description = "id del Carrera", required = true)
    @Column(name="CAR_Id")
    private Integer idCar;

    @ApiObjectField(description = "id Universidad", required = true)
    @Column(name="UNI_Id")
    private Integer idUni;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDetIdEsp() {
        return DetIdEsp;
    }

    public void setDetIdEsp(Integer detIdEsp) {
        DetIdEsp = detIdEsp;
    }

    public String getAsignatura() {
        return Asignatura;
    }

    public void setAsignatura(String asignatura) {
        Asignatura = asignatura;
    }

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public Integer getIdpai() {
        return idpai;
    }

    public void setIdpai(Integer idpai) {
        this.idpai = idpai;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public Integer getIdUni() {
        return idUni;
    }

    public void setIdUni(Integer idUni) {
        this.idUni = idUni;
    }

}
