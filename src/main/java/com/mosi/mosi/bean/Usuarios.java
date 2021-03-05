package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "USU_Usuarios")
public class Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id del Usuario", required = true)
    @Column(name="USU_Id")
    private Integer id;

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

    @OneToOne
    @JoinColumn(name="IMG_Id")
    private Imagen imagen;

    @OneToOne
    @JoinColumn(name="ROL_Id")
    private Roles roles;

    @Column(name="USU_Nombre")
    private String nombre;
    @Column(name="USU_Apellido")
    private String apellido;
    @Column(name="USU_Telefono")
    private String telefono;
    @Column(name="USU_CODPAIS")
    private String codigoPais;

    @Column(name = "USU_RESETEARCLAVETOKEN")
    private String tokenReseteo;
    @Column(name = "USU_FECHARESETEO")
    private Date fechaReseteo;

    @Column(name = "USU_INTENTOFALLIDO")
    private Integer intentoFallido;

    public Integer getIntentoFallido() {
        return intentoFallido;
    }

    public void setIntentoFallido(Integer intentoFallido) {
        this.intentoFallido = intentoFallido;
    }

    public String getTokenReseteo() {
        return tokenReseteo;
    }

    public void setTokenReseteo(String tokenReseteo) {
        this.tokenReseteo = tokenReseteo;
    }

    public Date getFechaReseteo() {
        return fechaReseteo;
    }

    public void setFechaReseteo(Date fechaReseteo) {
        this.fechaReseteo = fechaReseteo;
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

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
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



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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






}
