package com.is2.web.app.models.dao;

import java.util.List;

import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Tarea;

public interface ITareaDao {
	
    public List<Tarea> findAll();
    
    public List<Tarea> findTareasLibres();
	
    public void save(Tarea tarea);
	
    public Tarea findOne(long id);

    public Tarea findTarea(String codigoTarea);
	
    public List<Tarea> findLineaBase(String codLineaBase);
        
    public List<Tarea> findProyecto(String codigoProyecto);
    
    public void desbloquearTarea(String codLineaBase);
        
    public void bloquearTarea(String codLineaBase);
    
    public void removeTarea(Tarea tarea);
    
    public List<Tarea> findTareasPadre(String codigoTarea);
    
    public List<Tarea> findNoBloqueadas();
}
