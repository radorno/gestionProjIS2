package com.is2.web.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tareas")
public class Tarea {

	@Id
	private long Id;
	private String version;
	private String priodidad;
	private String estado;
	private String descripcion;
	private String observacion;
	private long idTareaPadre;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPriodidad() {
		return priodidad;
	}
	public void setPriodidad(String priodidad) {
		this.priodidad = priodidad;
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
	public long getIdTareaPadre() {
		return idTareaPadre;
	}
	public void setIdTareaPadre(long idTareaPadre) {
		this.idTareaPadre = idTareaPadre;
	}
	
	
	
	
}
