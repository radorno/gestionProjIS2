package com.is2.web.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.is2.web.app.models.dao.IProyectoDao;
import com.is2.web.app.models.dao.IRolDao;
import com.is2.web.app.models.dao.ITareaDao;
import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Tarea;
import com.is2.web.app.models.entity.Usuario;

@Controller
@RequestMapping("/app")
public class DesarrolladorController {
	
	@Autowired
	private IProyectoDao proyectoDao;
	@Autowired
	private ITareaDao tareaDao;
	
	
	@GetMapping({"/desarrollo"})       
	public String menuDesarrollo(){
		return "desarrollador/desarrollo";
	}
	
	@GetMapping({"/desarrollo/gestionProyecto"})       
	public String gestionProyecto(Model model){
		return "desarrollador/gestionProyecto";
	}
	
	@GetMapping({"/desarrollo/gestionTarea"})       
	public String gestionTarea(Model model){
		return "desarrollador/gestionTarea";
	}
	
	
	@GetMapping({"/desarrollo/crearProyecto"})       
	public String crearProyecto(Map<String, Object> model){
		Proyecto proyecto = new Proyecto();
		model.put("proyecto",proyecto);
		return "desarrollador/crearProyecto";
	}
	
	
	@RequestMapping(value="/desarrollo/crearProyecto", method=RequestMethod.POST)       
	public String guardarRoles(Proyecto proyecto){

		proyectoDao.save(proyecto);
		return "desarrollador/gestionProyecto";
	}
	
	@GetMapping({"/desarrollo/modificarProyecto"})       
	public String modificarProyecto(Map<String, Object> model){
		Proyecto proyectoNuevo= new Proyecto();
		model.put("proyecto",proyectoNuevo);
		return "desarrollador/modificarProyecto";
	}
	
	
	@RequestMapping(value="/desarrollo/modificarProyecto", method=RequestMethod.POST)
	public String modificarProyecto(Proyecto proyectoNuevo, Map<String, Object> model) {
		
		Proyecto proyecto = null;
		proyecto = proyectoDao.findOne(proyectoNuevo.getIdProyecto());
		model.put("proyecto", proyecto);
		
		return "desarrollador/crearProyecto";
	}
	

	
	
	
	@GetMapping({"/desarrollo/modificarTarea"})       
	public String modificarTarea(Map<String, Object> model){
		Tarea tareaNuevo= new Tarea();
		model.put("tarea",tareaNuevo);
		return "desarrollador/modificarTarea";
	}
	
	@RequestMapping(value="/desarrollo/modificarTarea", method=RequestMethod.POST)
	public String modificarTarea(Tarea tareaNuevo, Map<String, Object> model) {
		
		Tarea tarea = null;
		tarea = tareaDao.findOne(tareaNuevo.getId());
		model.put("tarea", tarea);
		
		return "desarrollador/crearTarea";
	}
	

	
	@GetMapping({"/desarrollo/crearTarea"})       
	public String crearTarea(Map<String, Object> model){
		Tarea tarea = new Tarea();
		model.put("tarea",tarea);
		return "desarrollador/crearTarea";
	}
	

	
	@RequestMapping(value="/desarrollo/crearTarea", method=RequestMethod.POST)       
	public String guardarTarea(Tarea tarea){

		tareaDao.save(tarea);
		return "desarrollador/gestionTarea";
	}
	
	
	

}
