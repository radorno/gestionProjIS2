package com.is2.web.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Usuario;

public class RolDaoImpl implements IRolDao  {
	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("inchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Rol").getResultList();
	}

	@Override
	public void save(Rol rol) {
		// TODO Auto-generated method stub
		em.persist(rol);
	}
	
	
	

}
