package com.is2.web.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Tarea;

@Repository
public class TareaDaoImpl implements ITareaDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("inchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Tarea> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Tarea").getResultList();
	}

	@Override
	@Transactional
	public void save(Tarea tarea) {
		if (tarea.getId() != 0) {

			em.merge(tarea);
		} else {

			em.persist(tarea);
		}
	}

	@Override
	public Tarea findOne(long id) {
		// TODO Auto-generated method stub
		return em.find(Tarea.class, id);
	}

}
