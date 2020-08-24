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
import com.is2.web.app.models.dao.IUsuarioDao;
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
	@Autowired
	private IUsuarioDao usuarioDao;
	
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
	
		Usuario usuario = new Usuario();
		usuario = usuarioDao.findUser(proyecto.getAdmin());
		if (result.hasErrors()) {
			model.addAttribute("error", "error volver a cargar campos");
			return "desarrollador/crearProyecto";
		}
		
		if(usuario == null){
			model.addAttribute("error", "error Admin no existe en base de datos");
			return "desarrollador/crearProyecto";
		}
		

		if (proyectoDao.findProyecto(proyecto.getCodigoProyecto()) != null) {

			if (proyecto.getId() == 0) {
				model.addAttribute("error", "error Proyecto ya existe dentro de la base de datos");
				return "desarrollador/crearProyecto";
			} else {
				proyectoDao.save(proyecto);
				model.addAttribute("error", "Proyecto creado con exito");
				return "desarrollador/crearProyecto";
			}
		} else {

			proyectoDao.save(proyecto);
			model.addAttribute("error", "Proyecto creado con exito");
			return "desarrollador/crearProyecto";
		}

	}

        
        @GetMapping({ "/desarrollo/verProyectos" })
        public String verProyectos(Map<String, Object> model) {
		
            model.put("proyectos", proyectoDao.findAll());
            return "desarrollador/verProyectos";
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
		if (proyecto == null) {
			models.addAttribute("error", "error Proyecto no existe");
			return "desarrollador/modificarProyecto";
		} else {

			model.put("proyecto", proyecto);
			model.put("error", "");

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
                   
                    if(tarea.getEstado().equals("BLOQUEADO")){
			models.addAttribute("error", "error Tarea bloqueada no modificable");
			return "desarrollador/modificarTarea";
                    }else{
                        model.put("tarea", tarea);
			model.put("valid", valid);
			model.put("error", "");

			return "desarrollador/crearTarea";
                    
                    }    
		}

	}

        @GetMapping({ "/desarrollo/verTareas" })
        public String verTareas(Map<String, Object> model) {
		
            model.put("tareas", tareaDao.findAll());
            return "desarrollador/verTareas";
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

	@GetMapping({ "/desarrollo/asignarTarea" })
	public String asignarTarea(Map<String, Object> model) {
		Tarea tarea = new Tarea();

		model.put("tarea", tarea);
		model.put("error", "");
		return "desarrollador/asignarTarea";
	}

	@RequestMapping(value = "/desarrollo/asignarTarea", method = RequestMethod.POST)
	public String asignarTarea(Tarea tareaNuevo, Map<String, Object> model, Model models) {

		Tarea tarea = null;
		Proyecto proyecto = null;
		tarea = tareaDao.findTarea(tareaNuevo.getCodigoTarea());
		proyecto = proyectoDao.findProyecto(tareaNuevo.getCodigoProyecto());
		if (tarea == null) {
			models.addAttribute("error", "error Tarea no existe");
			return "desarrollador/asignarTarea";
		} else if (proyecto == null) {
			models.addAttribute("error", "error Proyecto no existe");
			return "desarrollador/asignarTarea";
		} else {
			tarea.setCodigoProyecto(tareaNuevo.getCodigoProyecto());
			model.put("error", "Tarea asignada con exito");
			tareaDao.save(tarea);
			return "desarrollador/asignarTarea";
		}
	}

	@GetMapping({ "/desarrollo/conectarTarea" })
	public String conectarTarea(Map<String, Object> model) {
		Tarea tarea = new Tarea();

		model.put("tarea", tarea);
		model.put("error", "");
		return "desarrollador/conectarTarea";
	}

	@RequestMapping(value = "/desarrollo/conectarTarea", method = RequestMethod.POST)
	public String conectarTarea(Tarea tareaNuevo, Map<String, Object> model, Model models) {

		Tarea tarea = null;
		Tarea tareaPadre = null;
		tarea = tareaDao.findTarea(tareaNuevo.getCodigoTarea());
		tareaPadre = tareaDao.findTarea(tareaNuevo.getTareaPadre());

		if (tarea == null) {
			models.addAttribute("error", "error Tarea no existe");
			return "desarrollador/conectarTarea";
		} else if (tareaPadre == null) {
			models.addAttribute("error", "error Tarea Padre no existe");
			return "desarrollador/conectarTarea";
		} else if (tarea.getCodigoTarea() == tareaPadre.getTareaPadre()) {
			models.addAttribute("error",
					"error de coneccion la se quiere asignar en tareaPadre la tarea del hijo como tarea padre");
			return "desarrollador/conectarTarea";

		} else {
			tarea.setTareaPadre(tareaNuevo.getTareaPadre());
			model.put("error", "Tarea conectada con exito");
			tareaDao.save(tarea);
			return "desarrollador/conectarTarea";
		}
	}

}
