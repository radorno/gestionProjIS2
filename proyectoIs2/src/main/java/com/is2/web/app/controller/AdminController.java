package com.is2.web.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.is2.web.app.models.dao.IProyectoDao;
import com.is2.web.app.models.dao.IRolDao;
import com.is2.web.app.models.dao.IUsuarioDao;
import com.is2.web.app.models.entity.Rol;
import com.is2.web.app.models.entity.Usuario;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/app")
public class AdminController {
	@Autowired
	private IRolDao rolDao;
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
			
			model.put("roles",rolDao.findAll());
			model.put("usuario",usuario);
			model.put("error","");
			return "administrador/crearUsuarios";
		}
                
               
		@RequestMapping(value="/administrativo/crearUsuarios", method=RequestMethod.POST)       
		public String guardarUsuario(@Valid Usuario usuario, BindingResult result, Model model,Map<String, Object> models){

			if(result.hasErrors()) {
				model.addAttribute("error","error volver a cargar campos" );
				models.put("roles",rolDao.findAll());
				return "administrador/crearUsuarios";
			}
			
			
			if( usuarioDao.findUser(usuario.getUserCode()) != null ) {
				
				if (usuario.getId() == 0) {
					model.addAttribute("error","error usuario ya existe dentro de la base de datos" );
					models.put("roles",rolDao.findAll());
					return "administrador/crearUsuarios";
					} else {
						usuarioDao.save(usuario);
						return  "administrador/gestionRoles";
					}
			}else {
			
			usuarioDao.save(usuario);
			return "administrador/gestionUsuario";
			}
		}
		
		@GetMapping({"/administrativo/modificarUsuario/{userCode}"})       
		public String modificarUsuario(@PathVariable("userCode") String userCode,Map<String, Object> model){
			Usuario usuario = null;
                        usuario = usuarioDao.findUser(userCode);
			model.put("roles",rolDao.findAll());
			model.put("usuario",usuario);
			model.put("error","");
			return "administrador/modificarUsuario";
		}
		
                @RequestMapping(value="/administrativo/modificarUsuario", method=RequestMethod.POST)       
		public String modificarUsuario(@Valid Usuario usuario, BindingResult result, Model model,Map<String, Object> models){

			if(result.hasErrors()) {
				model.addAttribute("error","error volver a cargar campos" );
				models.put("roles",rolDao.findAll());
				return "administrador/modificarUsuario";
			}
			
			
			if( usuarioDao.findUser(usuario.getUserCode()) != null ) {
				
				if (usuario.getId() == 0) {
					model.addAttribute("error","error usuario ya existe dentro de la base de datos" );
					models.put("roles",rolDao.findAll());
					return "administrador/modificarUsuario";
					} else {
						usuarioDao.save(usuario);
                                                model.addAttribute("usuarios",usuarioDao.findAll());
						return  "administrador/verUsuarios";
					}
			}else {
			
			usuarioDao.save(usuario);
                        model.addAttribute("usuarios",usuarioDao.findAll());
			return "administrador/verUsuarios";
			}
		}
                
                @GetMapping({"/administrativo/modificarUsuarios"})       
		public String modificarUsuario(Map<String, Object> model){
			Usuario usuario = new Usuario();
			
			model.put("roles",rolDao.findAll());
			model.put("usuario",usuario);
			model.put("error","");
			return "administrador/modificarUsuarios";
		}
                
                @GetMapping({"/administrativo/eliminarUsuario/{userCode}"})       
		public String eliminarUsuario(@PathVariable("userCode") String userCode, Map<String, Object> model,Model models){
			Usuario usuario = usuarioDao.findUser(userCode);
			usuarioDao.removeUsuario(usuario);
			models.addAttribute("usuarios",usuarioDao.findAll());
			return "administrador/verUsuarios";
		}
                
                @GetMapping({ "/administrativo/verUsuarios" })
                public String verUsuarios(Model model) {
		
                    model.addAttribute("usuarios", usuarioDao.findAll());
                    return "administrador/verUsuarios";
                } 
              
		
		@GetMapping({"/administrativo/gestionUsuario"})       
		public String gestionUsuario(Model model){
			return "administrador/gestionUsuario";
		}

		
		@GetMapping({"/administrativo/gestionRoles"})       
		public String gestionRoles(Model model){
			return "administrador/gestionRoles";
		}
		
		@GetMapping({"/administrativo/crearRoles"})       
		public String crearRoles(Map<String, Object> model){
			Rol rol = new Rol();
			model.put("rol",rol);
			model.put("error","");

			return "administrador/crearRoles";			
			
		}
		
		@RequestMapping(value="/administrativo/crearRoles", method=RequestMethod.POST)       
		public String guardarRoles(@Valid Rol rol, BindingResult result, Model model){
			
			if(result.hasErrors()) {
				model.addAttribute("error","error volver a cargar campos" );
				return "administrador/crearRoles";
			}
			
			if( rolDao.findRol(rol.getNombreRol()) != null ) {
				if (rol.getId() == 0) {
				model.addAttribute("error","error Rol ya existe dentro de la base de datos" );
				return "administrador/crearRoles";
				} else {
					rolDao.save(rol);
					return  "administrador/gestionRoles";
				}
			}else {
			
				rolDao.save(rol);
				return "administrador/gestionRoles";
			}
			
			
		}
		
		@GetMapping({ "/administrativo/verRoles" })
                public String verRoles(Map<String, Object> model) {
		
		model.put("roles", rolDao.findAll());
		return "administrador/verRoles";
                } 
		
		@RequestMapping(value="/administrativo/modificarRoles", method=RequestMethod.POST)
		public String modificarRoles(Rol rolNuevo, Map<String, Object> model,Model models) {
			Rol rol = null;
			rol = rolDao.findRol(rolNuevo.getNombreRol());
			if(rol == null) {
				models.addAttribute("error","error Rol no existe" );
				return "administrador/modificarRoles";
			}else {
			
			model.put("rol", rol);
			model.put("error","");
			
			return "administrador/crearRoles";
			}
			
		}
		
		@GetMapping({"/administrativo/modificarRoles"})       
		public String modificarRoles(Map<String, Object> model){
			Rol rolNuevo= new Rol();
			model.put("rol",rolNuevo);
			return "administrador/modificarRoles";
		}
		
		@GetMapping({"/administrativo/asignarRol/{userCode}"})       
		public String asignarRol(@PathVariable("userCode") String userCode, Map<String, Object> model){
			Usuario usuario = usuarioDao.findUser(userCode);
			
                        model.put("roles",rolDao.findAll());
			model.put("usuario",usuario);
			model.put("error","");
                        model.put("title","Asignar Rol a Usuario : "+ usuario.getUserCode());
			return "administrador/asignarRol";
		}
		
		@RequestMapping(value="/administrativo/asignarRol", method=RequestMethod.POST)
		public String asignarRol(Usuario usuarioNuevo, Map<String, Object> model, Model models) {
			
			Usuario usuario = null;
			Rol     rol     = null;
			usuario = usuarioDao.findUser(usuarioNuevo.getUserCode());
			rol = rolDao.findRol(usuarioNuevo.getNombreRol());
			if(usuario == null) {
				models.addAttribute("error","error Usuario no existe" );
				return "administrador/asignarRol";
			}else if(rol == null) {
				models.addAttribute("error","error Rol no existe" );
				return "administrador/asignarRol";
			}else {
			usuario.setNombreRol(usuarioNuevo.getNombreRol());	
			model.put("error","Rol asignado con exito");
			usuarioDao.save(usuario);
                        models.addAttribute("usuarios",usuarioDao.findAll());
			return "administrador/verUsuarios";
			}
		}
		
}
