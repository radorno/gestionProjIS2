package com.is2.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class IndexControllers {
	
	@GetMapping({"/index","/"})       
	public String index(){
		return "index";
	}
	
	

}
