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
		return "news";
	}
	
	//ESTO LO PUSE ACA PARA PROBAR SI ANDABA NADA MAS
	@GetMapping("/capacitaciones")
	public String capacitaciones() {
		return "trainings";
	}
	
	//ESTO LO PUSE ACA PARA PROBAR SI ANDABA NADA MAS X2
	@GetMapping("/ayuda")
	public String ayuda() {
		return "help";
	}
	
	
}
