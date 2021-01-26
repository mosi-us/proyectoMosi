package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HOM_PASATIEMPOMAESTRO")
public class PasatiempoMaestro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiObjectField(description = "Id", required = true)
    @Column(name="HOM_Id")
    private Integer id;

    @ApiObjectField(description = "Id de Estudiante", required = true)
    @Column(name="EST_Id")
    private Integer estId;

    @ApiObjectField(description = "Id de Detalle Estudiante", required = true)
    @Column(name="DET_Id")
    private Integer detId;

    @ApiObjectField(description = "Id de Pasatiempo", required = true)
    @Column(name="HOB_Id")
    private Integer hobId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    public Integer getDetId() {
        return detId;
    }

    public void setDetId(Integer detId) {
        this.detId = detId;
    }

    public Integer getHobId() {
        return hobId;
    }

    public void setHobId(Integer hobId) {
        this.hobId = hobId;
    }
}
