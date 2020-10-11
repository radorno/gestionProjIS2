package com.is2.web.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Rol;
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

    @Override
    public Tarea findTarea(String codigoTarea) {
        List<Tarea> tarea;
        Query nativeQuery = em.createNativeQuery("SELECT * FROM TAREAS WHERE CODIGO_TAREA = :tarea", Tarea.class);
        nativeQuery.setParameter("tarea", codigoTarea);
        tarea = nativeQuery.getResultList();
        if (!tarea.isEmpty()) {
            return tarea.get(0);
        } else {

            return null;
        }
    }

    @Override
    public List<Tarea> findLineaBase(String codLineaBase) {
        List<Tarea> tarea;
        Query nativeQuery = em.createNativeQuery("SELECT * FROM TAREAS WHERE COD_LINEA_BASE = :codigo", Tarea.class);
        nativeQuery.setParameter("codigo", codLineaBase);
        tarea = nativeQuery.getResultList();
        if (!tarea.isEmpty()) {
            return tarea;
        } else {

            return null;
        }
    }

    @Override
    @Transactional
    public void desbloquearTarea(String codLineaBase) {

        List<Tarea> tarea;
        Query nativeQuery = em.createNativeQuery("SELECT * FROM TAREAS WHERE COD_LINEA_BASE = :codigo", Tarea.class);
        nativeQuery.setParameter("codigo", codLineaBase);
        tarea = nativeQuery.getResultList();
        if (!tarea.isEmpty()) {

            for (int i = 0; i < tarea.size(); i++) {
                tarea.get(i).setCodLineaBase(" ");
                tarea.get(i).setEstado("FINALIZADO");
                em.merge(tarea.get(i));

            }
        }

    }

    @Override
    public void bloquearTarea(String codLineaBase) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("inchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Tarea> findTareasLibres() {
        List<Tarea> tarea;
        Query nativeQuery = em.createNativeQuery("SELECT * FROM TAREAS WHERE  CODIGO_PROYECTO = '' OR CODIGO_PROYECTO  IS NULL ", Tarea.class);
        tarea = nativeQuery.getResultList();
        if (!tarea.isEmpty()) {
            return tarea;
        } else {

            return null;
        }
    }

    @Override
    public List<Tarea> findProyecto(String codigoProyecto) {
        List<Tarea> tarea;
        Query nativeQuery = em.createNativeQuery("SELECT * FROM TAREAS WHERE CODIGO_PROYECTO = :codigo", Tarea.class);
        nativeQuery.setParameter("codigo", codigoProyecto);
        tarea = nativeQuery.getResultList();
        if (!tarea.isEmpty()) {
            return tarea;
        } else {

            return null;
        }
    }

    @Override
    @Transactional
    public void removeTarea(Tarea tarea) {
        Tarea t = em.find(Tarea.class, tarea.getId());
        System.out.println(tarea.getId());
        em.remove(t);
    }

    @Override
    public List<Tarea> findTareasPadre(String codigoTarea) {
        List<Tarea> tarea;
        Query nativeQuery = em.createNativeQuery("SELECT * FROM TAREAS WHERE CODIGO_TAREA != :codigo  AND (TAREA_PADRE = '' OR TAREA_PADRE IS NULL)", Tarea.class);
        nativeQuery.setParameter("codigo", codigoTarea);
        tarea = nativeQuery.getResultList();
        if (!tarea.isEmpty()) {
            return tarea;
        } else {

            return null;
        }
    }

    @Override
    public List<Tarea> findNoBloqueadas() {
        List<Tarea> tarea;
        Query nativeQuery = em.createNativeQuery("SELECT * FROM TAREAS WHERE COD_LINEA_BASE = '' OR COD_LINEA_BASE IS NULL ", Tarea.class);
        tarea = nativeQuery.getResultList();
        if (!tarea.isEmpty()) {
            return tarea;
        } else {

            return null;
        }
    }

}
