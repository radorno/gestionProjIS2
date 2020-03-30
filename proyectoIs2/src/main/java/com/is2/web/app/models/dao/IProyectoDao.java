package com.is2.web.app.models.dao;

import java.util.List;

import com.is2.web.app.models.entity.Proyecto;

public interface IProyectoDao {
	
	public List<Proyecto> findAll();
	
	public void save(Proyecto proyecto);

}
