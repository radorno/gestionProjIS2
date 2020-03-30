package com.is2.web.app.models.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Rol {

	@Id
	private long Id;
	private String nombreRol;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	
	
	
}
