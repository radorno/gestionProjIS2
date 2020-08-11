package com.is2.web.app.models.dao;

import java.util.List;

import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Tarea;

public interface ITareaDao {
	
    public List<Tarea> findAll();
	
	public void save(Tarea tarea);
	
	public Tarea findOne(long id);

	public Tarea findTarea(String codigoTarea);
}
