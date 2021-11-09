package com.MigraEmprende.MigraEmprende.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/leyes")
	public String leyes() {	
		return "seccion-leyes";
	}
	
	@GetMapping("/noticias")
	public String noticias() {
		return "seccion-leyes";
	}
}
