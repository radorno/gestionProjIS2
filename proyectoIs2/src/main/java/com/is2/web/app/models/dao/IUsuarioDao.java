package com.is2.web.app.models.dao;

import java.util.List;

import com.is2.web.app.models.entity.Usuario;

public interface IUsuarioDao {

	public List<Usuario> findAll();
	
	public void save(Usuario usuario);
	
	public Usuario findOne(long id);
	
	public boolean validarUser(Usuario usuario);
	
	public Usuario findUser(String userCode);
	
	public void updateRol(Usuario usuario);
}
