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
import com.is2.web.app.models.dao.ILineaBaseDao;
import com.is2.web.app.models.dao.IRolDao;
import com.is2.web.app.models.dao.ITareaDao;
import com.is2.web.app.models.dao.IUsuarioDao;
import com.is2.web.app.models.entity.LineaBase;
import com.is2.web.app.models.entity.Proyecto;
import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Tarea;
import com.is2.web.app.models.entity.Usuario;

@Controller
@RequestMapping("/app")
public class GestionController {
	
	@Autowired
	private ITareaDao tareaDao;
	@Autowired
	private ILineaBaseDao lineaBaseDao;
	
	@GetMapping({ "/gestion" })
	public String menuGestion() {
		return "gestion/moduloGestion";
	}
	
	@GetMapping({ "/gestion/crearLineaBase" })
	public String crearLineaBase(Map<String, Object> model) {
		LineaBase lineaBase = new LineaBase();
		model.put("lineaBase", lineaBase);
		model.put("error", "");
		return "gestion/crearLineaBase";

	}

	@RequestMapping(value = "/gestion/crearLineaBase", method = RequestMethod.POST)
	public String crearLineaBase(@Valid LineaBase lineaBase, BindingResult result, Model model) {
	
		
		if (result.hasErrors()) {
			model.addAttribute("error", "error volver a cargar campos");
			return "gestion/crearLineaBase";
		}

		

		if (lineaBaseDao.findLineaBase(lineaBase.getCodigo()) != null) {

			if (lineaBase.getId() == 0) {
				model.addAttribute("error", "error LineaBase ya existe dentro de la base de datos");
				return "gestion/crearLineaBase";
			} else {
				lineaBaseDao.save(lineaBase);
				model.addAttribute("error", "LineaBase creada con exito");
				return "gestion/crearLineaBase";
			}
		} else {

			lineaBaseDao.save(lineaBase);
			model.addAttribute("error", "LineaBase creado con exito");
			return "gestion/crearLineaBase";
		}

	}
	
	@GetMapping({ "/gestion/listarlb" })
	public String menuLineaBase() {
		return "gestion/listarlb";
	}
	
	@GetMapping({ "/gestion/listar_tareas" })
	public String verLineaBase(Map<String, Object> model) {
		
		model.put("tareas", tareaDao.findAll());
		return "gestion/listar_tareas";
	}
	
	
	@GetMapping({ "/gestion/buscar_linea_base" })
	public String buscarLineaBase(Map<String, Object> model) {
		LineaBase lineaBaseNuevo = new LineaBase();
		model.put("lineaBase",lineaBaseNuevo);
		return "/gestion/buscar_linea_base";
	}
	
	@RequestMapping(value="/gestion/buscar_linea_base", method=RequestMethod.POST)
	public String modificarUsuario(LineaBase lineaBaseNuevo, Map<String, Object> model, Model models) {
		
		LineaBase lineaBase = null;
		lineaBase = lineaBaseDao.findLineaBase(lineaBaseNuevo.getCodigo());
		if(lineaBase == null) {
			models.addAttribute("error","error Linea Base no existe" );
			return "gestion/buscar_linea_base";
		}else {
		
		
		model.put("lineaBase", lineaBase);
		model.put("error","");
		
		return "gestion/ver_linea_base";
		}
	}
	
	
	
	
}
