package com.is2.web.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is2.web.app.models.entity.LineaBase;
import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Rol;

@Repository
public class LineaBaseDaoImpl implements ILineaBaseDao{

	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("inchecked")
	@Transactional(readOnly=true)
	@Override
	public List<LineaBase> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from LineaBase").getResultList();
	}

	@Override
	@Transactional
	public void save(LineaBase lineaBase) {
		// TODO Auto-generated method stub
		 if(lineaBase.getId()!=0) {
				
				em.merge(lineaBase);
			}else {
			
			em.persist(lineaBase);
			}
	}
	
	@Override
	public LineaBase findOne(long id) {
		// TODO Auto-generated method stub
		return em.find(LineaBase.class, id);
	}

	@Override
	public LineaBase findLineaBase(String codigo) {
		List<LineaBase> lineaBase;
		Query nativeQuery = em.createNativeQuery("SELECT * FROM LINEA_BASE WHERE CODIGO = :codigo", LineaBase.class);
		nativeQuery.setParameter("codigo", codigo);
		lineaBase = nativeQuery.getResultList();
		if (!lineaBase.isEmpty()) {
			return lineaBase.get(0);
		} else {

			return null;
		}
		
	}

    @Override
    @Transactional
    public void removeLineaBase(LineaBase lineaBase) {
       LineaBase l = em.find(LineaBase.class, lineaBase.getId());
        System.out.println(lineaBase.getId());
       em.remove(l);
        
    
    }
	
	

	

}