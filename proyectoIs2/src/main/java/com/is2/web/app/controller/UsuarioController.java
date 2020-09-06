package com.is2.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.is2.web.app.models.dao.IUsuarioDao;

@Controller
@RequestMapping("/app")
public class UsuarioController {
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	

}
