package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="EST_ESTUDIANTES")
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id del Estudiante", required = true)
    @Column(name="EST_id")
    private Integer id;

    @ApiObjectField(description = "Nombre de Estudiante", required = true)
    @Column(name="EST_Nombres")
    private String nombre;

    @ApiObjectField(description = "Apellido del estudiante", required = true)
    @Column(name="EST_Apellidos")
    private String apellido;

    @ApiObjectField(description = "Fecha de Nacimiento", required = true)
    @Column(name="EST_Fecha_Nac")
    private Integer fechaNac;

    @ApiObjectField(description = "id del Pais", required = true)
    @Column(name="PAI_Id")
    private Integer idpai;

    @ApiObjectField(description = "id del Carrera", required = true)
    @Column(name="CAR_Id")
    private Integer idCar;

    @ApiObjectField(description = "id Universidad", required = true)
    @Column(name="UNI_Id")
    private Integer idUni;

    @ApiObjectField(description = "Es principal", required = true)
    @Column(name="EST_Principal")
    private Integer estPrincipal;

    @ApiObjectField(description = "id del usuario", required = true)
    @Column(name="USU_Id")
    private Integer usuid;

    @ApiObjectField(description = "Semestre", required = true)
    @Column(name="EST_Sem")
    private Integer semestre;

    @ApiObjectField(description = "Descripcion", required = true)
    @Column(name="Est_Descripcion")
    private String descripcion;

    @ApiObjectField(description = "telefono", required = true)
    @Column(name="EST_Telefono")
    private String telefono;

    @ApiObjectField(description = "Codigo del Pais", required = true)
    @Column(name="EST_CODPAIS")
    private String codigoPais;

    @ApiObjectField(description = "Correo", required = true)
    @Column(name="EST_Email")
    private String correo;

    @ApiObjectField(description = "Ciudad", required = true)
    @Column(name="CIU_Id")
    private Integer idCiudad;

    @ApiObjectField(description = "Lugar", required = true)
    @Column(name="EST_Lugar")
    private Integer lugar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Integer fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Integer getIdpai() {
        return idpai;
    }

    public void setIdpai(Integer idpai) {
        this.idpai = idpai;
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

    public Integer getEstPrincipal() {
        return estPrincipal;
    }

    public void setEstPrincipal(Integer estPrincipal) {
        this.estPrincipal = estPrincipal;
    }

    public Integer getUsuid() {
        return usuid;
    }

    public void setUsuid(Integer usuid) {
        this.usuid = usuid;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Integer getLugar() {
        return lugar;
    }

    public void setLugar(Integer lugar) {
        this.lugar = lugar;
    }
}
