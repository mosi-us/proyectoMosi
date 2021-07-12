package com.mosi.mosi.bean;

import javax.persistence.*;

@Entity
@Table(name = "PUP_Publicaciones_Persona", schema = "dbo", catalog = "MOSI")
public class PublicacionesPersona {
    private Integer pupId;
    private Publicaciones publicacion;
    private Integer pupIdPersona;
    private Integer pupTipoPersona;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PUP_Id")
    public Integer getPupId() {
        return pupId;
    }

    public void setPupId(Integer pupId) {
        this.pupId = pupId;
    }

    @Basic
    @Column(name = "PUP_IDPERSONA")
    public Integer getPupIdPersona() {
        return pupIdPersona;
    }
    public void setPupIdPersona(Integer pupIdPersona) {
        this.pupIdPersona = pupIdPersona;
    }

    @OneToOne
    @JoinColumn(name = "PUB_Id")

    public Publicaciones getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicaciones publicacion) {
        this.publicacion = publicacion;
    }


}
