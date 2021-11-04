package com.MigraEmprende.MigraEmprende.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.MigraEmprende.MigraEmprende.services.EmprendimientoService;

@Controller
@RequestMapping("/emprendimiento")
public class EmprendimientoController {

	
	@Autowired
	private EmprendimientoService emprendimientoService;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/{id}")
	public String id(@PathVariable String id) {
		return "emprendimientoHTML";
	}
	
	
	@GetMapping("/form")
	public String form() {
		return "formEmprendimiento";
	}
	
	
	@PostMapping("/crear")
	public String crear(String nombre, String descripcion, String email, String username) throws Exception {
		emprendimientoService.crear(nombre, descripcion, email, username);
		return "redirect:/";
	}
	
	
	@PostMapping("/modificar/{id}")
	public String modificarId(@PathVariable String id, String nombre, String descripcion, String email, String username) throws Exception {
		emprendimientoService.modificar(nombre, descripcion, email, username, id);
		return "redirect:/";
	}
	
	
	@PostMapping("/delete/{id}")
	public String deleteId(@PathVariable String id) throws Exception {
		emprendimientoService.eliminar(id);
		return "redirect:/";
	}
	
	
	@PostMapping("/deshabilitar")
	public String deshabilitar(String id) throws Exception {
		emprendimientoService.baja(id);
		return "redirect:/";
	}
	
	@PostMapping("/habilitar")
	public String habilitar(String id) throws Exception {
		emprendimientoService.alta(id);
		return "redirect:/";
	}
	
	
	
}
