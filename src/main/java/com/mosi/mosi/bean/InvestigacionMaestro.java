package com.mosi.mosi.bean;

import org.jsondoc.core.annotation.ApiObjectField;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="INE_InvestigacionmMaestro")
public class InvestigacionMaestro implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @ApiObjectField(description = "Id", required = true)
        @Column(name="INE_Id")
        private Integer id;

        @Column(name="INE_Nombre")
        private String Titulo;

        @Column(name="INE_Descripcion")
        private String Descripcion;

        @Column(name="CAT_Id")
        private Integer idCategoria;

        @Column(name="EST_Id")
        private Integer idEstudiante;

        @Column(name="DET_Id")
        private Integer detalleEstudianteId;

        public InvestigacionMaestro() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitulo() {
            return Titulo;
        }

        public void setTitulo(String titulo) {
            Titulo = titulo;
        }

        public String getDescripcion() {
            return Descripcion;
        }

        public void setDescripcion(String descripcion) {
            Descripcion = descripcion;
        }

        public Integer getIdCategoria() {
            return idCategoria;
        }

        public void setIdCategoria(Integer idCategoria) {
            this.idCategoria = idCategoria;
        }

        public Integer getIdEstudiante() {
            return idEstudiante;
        }

        public void setIdEstudiante(Integer idEstudiante) {
            this.idEstudiante = idEstudiante;
        }

        public Integer getDetalleEstudianteId() {
            return detalleEstudianteId;
        }

        public void setDetalleEstudianteId(Integer detalleEstudianteId) {
            this.detalleEstudianteId = detalleEstudianteId;
        }
    }

