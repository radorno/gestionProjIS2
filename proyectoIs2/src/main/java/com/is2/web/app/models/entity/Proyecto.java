package com.is2.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="proyectos")
public class Proyecto implements Serializable{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		private String codigoProyecto;
		private String Admin;
		private long Fase;
		private String descripcion;
		private String estado;
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern="yyyy-mm-dd")
		private Date fechaInicio;
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern="yyyy-mm-dd")
		private Date fechaFin;

		public String getCodigoProyecto() {
			return codigoProyecto;
		}
		public void setCodigoProyecto(String codigoProyecto) {
			this.codigoProyecto = codigoProyecto;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}

		
		public String getAdmin() {
			return Admin;
		}
	
		public long getFase() {
			return Fase;
		}
	
		public void setAdmin(String admin) {
			Admin = admin;
		}
	
		public void setFase(long fase) {
			Fase = fase;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public Date getFechaInicio() {
			return fechaInicio;
		}
		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}
		public Date getFechaFin() {
			return fechaFin;
		}
		public void setFechaFin(Date fechaFin) {
			this.fechaFin = fechaFin;
		}	
		
		
		
	}
	
	
	
	
	
	

