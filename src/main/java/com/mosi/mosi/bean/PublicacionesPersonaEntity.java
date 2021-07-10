package com.mosi.mosi.bean;

import javax.persistence.*;

@Entity
@Table(name = "PUP_Publicaciones_Persona", schema = "dbo", catalog = "MOSI")
public class PublicacionesPersonaEntity {
    private Integer pupId;
    private PublicacionesEntity publicacion;
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

    public PublicacionesEntity getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(PublicacionesEntity publicacion) {
        this.publicacion = publicacion;
    }
    @Basic
    @Column(name = "PUP_TIPOPERSONA")
    public Integer getPupTipoPersona() {
        return pupTipoPersona;
    }

    public void setPupTipoPersona(Integer pupTipoPersona) {
        this.pupTipoPersona = pupTipoPersona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicacionesPersonaEntity that = (PublicacionesPersonaEntity) o;

        if (pupId != that.pupId) return false;
        if (pupIdPersona != null ? !pupIdPersona.equals(that.pupIdPersona) : that.pupIdPersona != null) return false;
        if (pupTipoPersona != null ? !pupTipoPersona.equals(that.pupTipoPersona) : that.pupTipoPersona != null)
            return false;

        return true;
    }

}
