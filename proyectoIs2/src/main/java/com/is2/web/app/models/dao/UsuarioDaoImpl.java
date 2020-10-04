package com.is2.web.app.models.dao;

import com.is2.web.app.models.entity.LineaBase;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is2.web.app.models.entity.Usuario;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("inchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Usuario").getResultList();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		if (usuario.getId() != 0) {

			em.merge(usuario);
		} else {

			em.persist(usuario);
		}
	}

	@Override
	public Usuario findOne(long id) {
		// TODO Auto-generated method stub
		return em.find(Usuario.class, id);

	}

	@Override
	public boolean validarUser(Usuario usuario) {
		// TODO Auto-generated method stub
		List<Usuario> user;
		Query nativeQuery = em.createNativeQuery("SELECT * FROM USUARIOS WHERE USER_CODE = :user AND PASSWORD = :pass ",
				Usuario.class);
		nativeQuery.setParameter("user", usuario.getUserCode());
		nativeQuery.setParameter("pass", usuario.getPassword());
		user = nativeQuery.getResultList();

		if (!user.isEmpty()) {

			return true;

		} else {

			return false;

		}

	}

	@Override
	public Usuario findUser(String userCode) {
		List<Usuario> user;
		Query nativeQuery = em.createNativeQuery("SELECT * FROM USUARIOS WHERE USER_CODE = :user", Usuario.class);
		nativeQuery.setParameter("user", userCode);
		user = nativeQuery.getResultList();
		if (!user.isEmpty()) {
			return user.get(0);
		} else {

			return null;
		}
	}

	@Override
	public void updateRol(Usuario usuario) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		Usuario usuarioDTO = em.find(Usuario.class, usuario.getId());
      
	
		  usuarioDTO.setNombreRol(usuario.getNombreRol());;
	     
		  em.getTransaction().commit();
		  em.close();
		
	}

        @Override
        @Transactional
        public void removeUsuario(Usuario usuario) {
            Usuario u = em.find(Usuario.class, usuario.getId());
            System.out.println(usuario.getId());
            em.remove(u);
        }	

}
