package com.mosi.mosi.bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "POS_POSTULACIONES", schema = "dbo", catalog = "MOSI")
public class Postulaciones {
    private int posId;
    private Integer asiId;
    private Integer empId;
    private Integer estId;
    private Integer detId;
    private Date posFecha;
    private Integer posEstatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POS_Id")
    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    @Basic
    @Column(name = "ASI_Id")
    public Integer getAsiId() {
        return asiId;
    }

    public void setAsiId(Integer asiId) {
        this.asiId = asiId;
    }

    @Basic
    @Column(name = "EMP_Id")
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    @Basic
    @Column(name = "EST_Id")
    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    @Basic
    @Column(name = "DET_Id")
    public Integer getDetId() {
        return detId;
    }

    public void setDetId(Integer detId) {
        this.detId = detId;
    }

    @Basic
    @Column(name = "POS_Fecha")
    public Date getPosFecha() {
        return posFecha;
    }

    public void setPosFecha(Date posFecha) {
        this.posFecha = posFecha;
    }

    @Basic
    @Column(name = "POS_Estatus")
    public Integer getPosEstatus() {
        return posEstatus;
    }

    public void setPosEstatus(Integer posEstatus) {
        this.posEstatus = posEstatus;
    }


}
