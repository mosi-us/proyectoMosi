package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "USU_Usuarios")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id del Usuario", required = true)
    @Column(name="USU_Id")
    private Integer id;

    @ApiObjectField(description = "Nombre de Usuario", required = true)
    @Column(name="USU_Nombre_Usuario")
    private String nombre;

    @ApiObjectField(description = "Email", required = true)
    @Column(name="USU_email")
    private String email;

    @ApiObjectField(description = "Clave ", required = true)
    @Column(name="USU_password")
    private String password;

    @ApiObjectField(description = "Estatus", required = true)
    @Column(name="USU_Estatus")
    private Integer estatus;

    @ApiObjectField(description = "Tipo de Persona", required = true)
    @Column(name="USU_TIPO")
    private Integer tipo_persona;

    @ApiObjectField(description = "Fecha de Creacion", required = true)
    @Column(name="USU_Fecha_Creacion")
    private Date fecha;

    @ApiObjectField(description = "Token")
    @Column(name="USU_Token")
    private String token;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(Integer tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Users{" +
                ", usuario='" + nombre + '\'' +
                ", email=" + email +
                '}';
    }



}
