package com.is2.web.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tareas")
public class Tarea implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String codigoTarea;
	private String version;
	private String prioridad;
	private String estado;
	private String descripcion;
	private String observacion;
	private String codigoProyecto;
	private String tareaPadre;
	private String codLineaBase;
	public long getId() {
		return id;
	}
	
	
	public String getCodLineaBase() {
		return codLineaBase;
	}


	public void setCodLineaBase(String codLineaBase) {
		this.codLineaBase = codLineaBase;
	}


	public String getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(String codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	public String getCodigoTarea() {
		return codigoTarea;
	}



	public String getTareaPadre() {
		return tareaPadre;
	}

	public void setTareaPadre(String tareaPadre) {
		this.tareaPadre = tareaPadre;
	}

	public void setCodigoTarea(String codigoTarea) {
		this.codigoTarea = codigoTarea;
	}



	public void setId(long id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	
	
	
}
