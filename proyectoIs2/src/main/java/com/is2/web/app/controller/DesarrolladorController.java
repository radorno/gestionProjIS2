package com.is2.web.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

	@GetMapping({ "/desarrollo" })
	public String menuDesarrollo() {
		return "desarrollador/desarrollo";
	}

	@GetMapping({ "/desarrollo/gestionProyecto" })
	public String gestionProyecto(Model model) {
		return "desarrollador/gestionProyecto";
	}

	@GetMapping({ "/desarrollo/gestionTarea" })
	public String gestionTarea(Model model) {
		return "desarrollador/gestionTarea";
	}

	@GetMapping({ "/desarrollo/crearProyecto" })
	public String crearProyecto(Map<String, Object> model) {
		Proyecto proyecto = new Proyecto();
		model.put("proyecto", proyecto);
		model.put("error", "");
		return "desarrollador/crearProyecto";
	
	}

	@RequestMapping(value = "/desarrollo/crearProyecto", method = RequestMethod.POST)
	public String guardarRoles(@Valid Proyecto proyecto, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("error","error volver a cargar campos" );
			return "desarrollador/crearProyecto";
		}
		
		
		if( proyectoDao.findProyecto(proyecto.getCodigoProyecto()) != null ) {
			
			if (proyecto.getId() == 0) {
				model.addAttribute("error","error Proyecto ya existe dentro de la base de datos" );
				return "desarrollador/crearProyecto";
				} else {
					proyectoDao.save(proyecto);
					return "desarrollador/gestionProyecto";
				}
		}else {
		
		proyectoDao.save(proyecto);
		return "desarrollador/gestionProyecto";
		}
		
	}

	@GetMapping({ "/desarrollo/modificarProyecto" })
	public String modificarProyecto(Map<String, Object> model) {
		
		Proyecto proyectoNuevo = new Proyecto();
		model.put("proyecto", proyectoNuevo);
		return "desarrollador/modificarProyecto";
	}

	@RequestMapping(value = "/desarrollo/modificarProyecto", method = RequestMethod.POST)
	public String modificarProyecto(Proyecto proyectoNuevo, Map<String, Object> model, Model models) {
		Proyecto proyecto = null;
		proyecto = proyectoDao.findProyecto(proyectoNuevo.getCodigoProyecto());
		if(proyecto == null) {
			models.addAttribute("error","error Proyecto no existe" );
			return "desarrollador/modificarProyecto";
		}else {
		
	
		model.put("proyecto", proyecto);
		model.put("error","");
		
		return "desarrollador/crearProyecto";
		}
		
		
		

		
	}

	@GetMapping({ "/desarrollo/modificarTarea" })
	public String modificarTarea(Map<String, Object> model) {
		Tarea tareaNuevo = new Tarea();
		model.put("tarea", tareaNuevo);
		return "desarrollador/modificarTarea";
	}

	@RequestMapping(value = "/desarrollo/modificarTarea", method = RequestMethod.POST)
	public String modificarTarea(Tarea tareaNuevo, Map<String, Object> model, Model models) {
		Tarea tarea = null;
		Integer valid = 1;
		tarea = tareaDao.findTarea(tareaNuevo.getCodigoTarea());
		if (tarea == null) {
			models.addAttribute("error", "error Tarea no existe");

			return "desarrollador/modificarTarea";
		} else {

			model.put("tarea", tarea);
			model.put("valid", valid);
			model.put("error", "");

			return "desarrollador/crearTarea";
		}

	}

	@GetMapping({ "/desarrollo/crearTarea" })
	public String crearTarea(Map<String, Object> model) {
		Tarea tarea = new Tarea();

		model.put("tarea", tarea);
		model.put("error", "");
		return "desarrollador/crearTarea";
	}

	@RequestMapping(value = "/desarrollo/crearTarea", method = RequestMethod.POST)
	public String guardarTarea(@Valid Tarea tarea, BindingResult result, Model model, Integer valid) {

		if (result.hasErrors()) {
			model.addAttribute("error", "error volver a cargar campos");
			return "desarrollador/crearTarea";
		}

		if (tareaDao.findTarea(tarea.getCodigoTarea()) != null) {
			if (tarea.getId() == 0) {
				model.addAttribute("error", "error Tarea ya existe dentro de la base de datos");
				return "desarrollador/crearTarea";
			} else {
				tareaDao.save(tarea);
				return "desarrollador/gestionTarea";
			}
		} else {

			tareaDao.save(tarea);
			return "desarrollador/gestionTarea";
		}

	}

}
