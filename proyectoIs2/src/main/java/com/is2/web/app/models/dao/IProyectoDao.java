package com.is2.web.app.models.dao;

import java.util.List;

import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Rol;

public interface IProyectoDao {
	
	public List<Proyecto> findAll();
	
	public void save(Proyecto proyecto);
	
	public Proyecto findOne(long id);
	
	public Proyecto findProyecto(String codigoProyecto);
        
        public void removeProyecto(Proyecto proyecto);

}
