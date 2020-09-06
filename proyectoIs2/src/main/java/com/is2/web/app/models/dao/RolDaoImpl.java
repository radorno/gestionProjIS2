package com.is2.web.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Usuario;

@Repository
public class RolDaoImpl implements IRolDao  {
	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("inchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Rol> findAll() {
		
		return em.createQuery("from Rol").getResultList();
	}

	@Override
	@Transactional
	public void save(Rol rol) {
        if(rol.getId()!=0) {
			
			em.merge(rol);
		}else {
		
		em.persist(rol);
		}
	}

	@Override
	public Rol findOne(long id) {
		// TODO Auto-generated method stub
		return em.find(Rol.class, id);
	}

	@Override
	public Rol findRol(String nombreRol) {
		List<Rol> rol;
		Query nativeQuery = em.createNativeQuery("SELECT * FROM ROLES WHERE NOMBRE_ROL = :name", Rol.class);
		nativeQuery.setParameter("name", nombreRol);
		rol = nativeQuery.getResultList();
		if (!rol.isEmpty()) {
			return rol.get(0);
		} else {

			return null;
		}
	}
	
	
	

}
