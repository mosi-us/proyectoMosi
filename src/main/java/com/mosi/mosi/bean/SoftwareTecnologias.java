package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SYT_SOFTWARETECNOLOGIAS", schema = "dbo", catalog = "MOSI")
public class SoftwareTecnologias {
    private Integer sytId;
    private String sytNombre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SYT_Id")
    public Integer getSytId() {
        return sytId;
    }

    public void setSytId(int sytId) {
        this.sytId = sytId;
    }

    @Basic
    @Column(name = "SYT_Nombre")
    public String getSytNombre() {
        return sytNombre;
    }

    public void setSytNombre(String sytNombre) {
        this.sytNombre = sytNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareTecnologias that = (SoftwareTecnologias) o;
        return sytId == that.sytId &&
                Objects.equals(sytNombre, that.sytNombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sytId, sytNombre);
    }
}
