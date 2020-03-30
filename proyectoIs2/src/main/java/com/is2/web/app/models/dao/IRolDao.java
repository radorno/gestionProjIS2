package com.is2.web.app.models.dao;

import java.util.List;

import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Usuario;

public interface IRolDao {
	
	public List<Rol> findAll();
	
	public void save(Rol rol);
	
}

