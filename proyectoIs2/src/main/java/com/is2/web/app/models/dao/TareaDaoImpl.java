package com.is2.web.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Tarea;

public class TareaDaoImpl implements ITareaDao {

	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("inchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Tarea> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Tarea").getResultList();
	}

	@Override
	public void save(Tarea tarea) {
		// TODO Auto-generated method stub
		em.persist(tarea);
	}


	
	
}
