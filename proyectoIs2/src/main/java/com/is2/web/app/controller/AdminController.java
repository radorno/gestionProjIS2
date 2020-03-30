package com.is2.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.is2.web.app.models.Usuarios;

@Controller
@RequestMapping("/app")
public class AdminController {
	
		@GetMapping({"/home"})       
		public String home(){
			return "administrador/home";
		}
		
		@GetMapping({"/administrativo"})       
		public String mostrarUsuario(){
			return "administrador/administrativo";
		}
		
		@GetMapping({"/administrativo/crearUsuario"})       
		public String crearUsuario(Model model){
			return "administrador/crearUsuario";
		}
		

		
		
}
