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

import com.is2.web.app.models.dao.IUsuarioDao;
import com.is2.web.app.models.entity.Usuario;

@Controller
@RequestMapping("/app")
public class IndexControllers {
	@Autowired
	private IUsuarioDao usuarioDao;

	@GetMapping({ "/index", "/" })
	public String index(Map<String, Object> model) {
		Usuario usuario = new Usuario();

		model.put("usuario", usuario);
		model.put("error", "");
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String validarUsuario(@Valid Usuario usuario, BindingResult result, Model model) {

		if (usuarioDao.validarUser(usuario)) {
			
			return "administrador/home";
			
		} else {
			model.addAttribute("error", "Usuario invalido");
			return "index";

		}

	}

}
