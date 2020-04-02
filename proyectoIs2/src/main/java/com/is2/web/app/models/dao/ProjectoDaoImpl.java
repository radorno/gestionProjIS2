package com.is2.web.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Rol;

@Repository
public class ProjectoDaoImpl implements IProyectoDao{

	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("inchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Proyecto> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Proyecto").getResultList();
	}

	@Override
	@Transactional
	public void save(Proyecto proyecto) {
		// TODO Auto-generated method stub
		 if(proyecto.getIdProyecto()!=0) {
				
				em.merge(proyecto);
			}else {
			
			em.persist(proyecto);
			}
	}
	
	@Override
	public Proyecto findOne(long idProyecto) {
		// TODO Auto-generated method stub
		return em.find(Proyecto.class, idProyecto);
	}
	
	

	

}
