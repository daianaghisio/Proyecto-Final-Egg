package com.MigraEmprende.MigraEmprende.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

	
	@GetMapping("/form")
	public String form() {
		return "formConsulta.html";
	}
	
	
	@PostMapping("/crear")
	public String crear() {
		return "redirect:/";
	}
	
	
	@PostMapping("/delete/{id}")
	public String deleteId(@PathVariable String id) {
		return "redirect:/";
	}
	
	
}
