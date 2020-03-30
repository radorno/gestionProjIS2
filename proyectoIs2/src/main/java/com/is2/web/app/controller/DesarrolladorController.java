package com.is2.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class DesarrolladorController {
	
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
	public String crearProyecto(Model model){
		return "desarrollador/crearProyecto";
	}
	
	@GetMapping({"/desarrollo/modificarProyecto"})       
	public String modificarProyecto(Model model){
		return "desarrollador/modificarProyecto";
	}

}
