package com.is2.web.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.is2.web.app.models.dao.IUsuarioDao;
import com.is2.web.app.models.entity.Usuario;

@Controller
@RequestMapping("/app")
public class AdminController {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
		@GetMapping({"/home"})       
		public String home(){
			return "administrador/home";
		}
		
		@GetMapping({"/administrativo"})       
		public String mostrarUsuario(){
			return "administrador/administrativo";
		}
		
		@GetMapping({"/administrativo/crearUsuarios"})       
		public String crearUsuario(Map<String, Object> model){
			Usuario usuario = new Usuario();
			model.put("usuario",usuario);
			return "administrador/crearUsuarios";
		}
		
		@RequestMapping(value="/administrativo/crearUsuarios", method=RequestMethod.POST)       
		public String guardarUsuario(Usuario usuario){

			usuarioDao.save(usuario);
			return "administrador/gestionUsuario";
		}
		
		
		
		@GetMapping({"/administrativo/gestionUsuario"})       
		public String gestionUsuario(Model model){
			return "administrador/gestionUsuario";
		}

		@GetMapping({"/administrativo/modificarUsuario"})       
		public String modificarUsuario(Model model){
			return "administrador/modificarUsuario";
		}
		
		@GetMapping({"/administrativo/gestionRoles"})       
		public String gestionRoles(Model model){
			return "administrador/gestionRoles";
		}
		
		@GetMapping({"/administrativo/crearRoles"})       
		public String crearRoles(Model model){
			return "administrador/crearRoles";
		}
		
		@GetMapping({"/administrativo/modificarRoles"})       
		public String modificarRoles(Model model){
			return "administrador/modificarRoles";
		}
		
}
