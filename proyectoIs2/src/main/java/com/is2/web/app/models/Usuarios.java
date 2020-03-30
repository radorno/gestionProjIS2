package com.is2.web.app.models;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class Usuarios {
	@NotEmpty
	private String id;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	private String userCode;
	@NotEmpty
	private String password;
	@NotEmpty
	private String IdRol;
	@NotEmpty
	private Date fechaCreacion;
	
	
	
	public Usuarios(String id,String nombre, String apellido, String userCode, String password) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.userCode = userCode;
		this.password = password;
		this.fechaCreacion = new java.util.Date();
	}
	
	
	
	public String getIdRol() {
		return IdRol;
	}

	public void setIdRol(String idRol) {
		IdRol = idRol;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	

}
