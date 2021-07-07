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
    private String fechaNac;

    @OneToOne
    @ApiObjectField(description = "id del Pais", required = true)
    @JoinColumn(name="PAI_Id")
    private Paises pais;

    @OneToOne
    @ApiObjectField(description = "id del Carrera", required = true)
    @JoinColumn(name="CAR_Id")
    private Carrera carrera;

    @OneToOne
    @ApiObjectField(description = "id Universidad", required = true)
    @JoinColumn(name="UNI_Id")
    private Universidad universidad;

    @ApiObjectField(description = "Es principal", required = true)
    @Column(name="EST_Principal")
    private Integer estPrincipal;

    @OneToOne
    @ApiObjectField(description = "id del usuario", required = true)
    @JoinColumn(name="USU_Id")
    private Usuarios usuario;

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

    @OneToOne
    @ApiObjectField(description = "Ciudad", required = true)
    @JoinColumn(name="CIU_Id")
    private Ciudades ciudad;

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

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public Integer getEstPrincipal() {
        return estPrincipal;
    }

    public void setEstPrincipal(Integer estPrincipal) {
        this.estPrincipal = estPrincipal;
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

    public Integer getLugar() {
        return lugar;
    }

    public void setLugar(Integer lugar) {
        this.lugar = lugar;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
