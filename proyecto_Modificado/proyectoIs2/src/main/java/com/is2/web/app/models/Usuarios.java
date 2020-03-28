package com.is2.web.app.models;

import java.util.Date;

public class Usuarios {
	private String nombre;
	private String apellido;
	private String userCode;
	private String password;
	private Date fechaCreacion;
	
	
	
	public Usuarios(String nombre, String apellido, String userCode, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.userCode = userCode;
		this.password = password;
		this.fechaCreacion = new java.util.Date();
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
