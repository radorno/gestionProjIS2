package com.is2.web.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is2.web.app.models.entity.Usuario;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao {
    @PersistenceContext
	private EntityManager em;
	@SuppressWarnings("inchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Usuario").getResultList();
	}
	@Override
	@Transactional
	public void save(Usuario usuario) {
		em.persist(usuario);
		
	}



}
