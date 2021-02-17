package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RUB_Rubro", schema = "dbo", catalog = "MOSI")
public class RubRubro {
    private int rubId;
    private String rubNombre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RUB_Id")
    public int getRubId() {
        return rubId;
    }

    public void setRubId(int rubId) {
        this.rubId = rubId;
    }

    @Basic
    @Column(name = "RUB_Nombre")
    public String getRubNombre() {
        return rubNombre;
    }

    public void setRubNombre(String rubNombre) {
        this.rubNombre = rubNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RubRubro rubRubro = (RubRubro) o;
        return rubId == rubRubro.rubId &&
                Objects.equals(rubNombre, rubRubro.rubNombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rubId, rubNombre);
    }
}
