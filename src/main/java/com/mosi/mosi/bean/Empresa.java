package com.mosi.mosi.bean;


import javax.persistence.*;

@Entity
@Table(name = "EMP_Empresa", schema = "dbo", catalog = "MOSI")
public class  Empresa {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_Id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EMP_Descripcion")
    private String Descripcion;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    @OneToOne
    @JoinColumn(name = "RUB_id")
    private RubRubro rubro;

    @Basic
    @Column(name = "EMP_Ubicacion")
    private String Ubicacion;

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    @Basic
    @Column(name = "EMP_SITIOWEB")
    private String SitioWeb;

    public String getSitioWeb() {
        return SitioWeb;
    }

    public void setSitioWeb(String SitioWeb) {
        this.SitioWeb = SitioWeb;
    }

    @Basic
    @Column(name = "EMP_Nombre")
    private String Nombre;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Basic
    @Column(name = "EMP_RAZONSOCIAL")
    private String Razonsocial;

    public String getRazonsocial() {
        return Razonsocial;
    }

    public void setRazonsocial(String Razonsocial) {
        this.Razonsocial = Razonsocial;
    }

    @Basic
    @Column(name = "EMP_Telefono")
    private String Telefono;

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    @Basic
    @Column(name = "EMP_Correo")
    private String Correo;

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    @Basic
    @Column(name = "EMP_Mision")
    private String Mision;

    public String getMision() {
        return Mision;
    }

    public void setMision(String Mision) {
        this.Mision = Mision;
    }

    @Basic
    @Column(name = "EMP_Vision")
    private String Vision;

    public String getVision() {
        return Vision;
    }

    public void setVision(String Vision) {
        this.Vision = Vision;
    }

    @OneToOne
    @JoinColumn(name = "PAI_Id")
    private Paises pais;

    @OneToOne
    @JoinColumn(name = "USU_Id")
    private Users users;

    public RubRubro getRubro() {
        return rubro;
    }

    public void setRubro(RubRubro rubro) {
        this.rubro = rubro;
    }

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}