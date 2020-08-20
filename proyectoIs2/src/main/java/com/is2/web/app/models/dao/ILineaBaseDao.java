package com.is2.web.app.models.dao;

import java.util.List;

import com.is2.web.app.models.entity.LineaBase;

public interface ILineaBaseDao {
	
	public List<LineaBase> findAll();
	
	public void save(LineaBase lineaBase);
	
	public LineaBase findOne(long id);
	
	public LineaBase findLineaBase(String codigo);

}
