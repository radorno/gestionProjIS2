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
@Entity
@Table(name="proyectos")
public class Proyecto implements Serializable{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long idProyecto;
		private long idRol;
		private long idAdmin;
		private long idUsuario;
		private long idFase;
		private String descripcion;
		private int estado;
		@Temporal(TemporalType.DATE)
		private Date fechaInicio;
		@Temporal(TemporalType.DATE)
		private Date fechaFin;
		public long getIdProyecto() {
			return idProyecto;
		}
		public void setIdProyecto(long idProyecto) {
			this.idProyecto = idProyecto;
		}
		public long getIdRol() {
			return idRol;
		}
		public void setIdRol(long idRol) {
			this.idRol = idRol;
		}
		public long getIdAdmin() {
			return idAdmin;
		}
		public void setIdAdmin(long idAdmin) {
			this.idAdmin = idAdmin;
		}
		public long getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(long idUsuario) {
			this.idUsuario = idUsuario;
		}
		public long getIdFase() {
			return idFase;
		}
		public void setIdFase(long idFase) {
			this.idFase = idFase;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public int getEstado() {
			return estado;
		}
		public void setEstado(int estado) {
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
	
	
	
	
	
	

