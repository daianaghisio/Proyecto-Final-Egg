package com.MigraEmprende.MigraEmprende.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.MigraEmprende.MigraEmprende.services.EmprendimientoService;

@Controller
@RequestMapping("/emprendimiento")
public class EmprendimientoController {
	
	@Autowired
	private EmprendimientoService emprendimientoService;
	
	
	@GetMapping("/") // Devuelve una página con cada emprendimiento
	public String index() {
		return "entrepreneurship-all";
	}
	
	
	@GetMapping("/{id}") //Devuelve un único emprendimiento
	public String id(@PathVariable String id) {
		return "entrepreneurship";
	}
	
	
	@GetMapping("/form") //Devuelve un formulario para crear y modificar un emprendimiento
	public String form() {
		return "entrepreneurship-form";
	}
	
	
	@PostMapping("/crear") // Envía los datos del formulario acá para crear un emprendimiento
	public String crear(MultipartFile archivo, String nombre, String descripcion, String email, String username) throws Exception {
		emprendimientoService.crear(archivo, nombre, descripcion, email, username);
		return "redirect:/";
	}
	
	
	@PostMapping("/modificar/{id}") // Envía los datos del formulario acá para editar un emprendimiento
	public String modificarId(@PathVariable String id, MultipartFile archivo, String nombre, String descripcion, String email, String username) throws Exception {
		emprendimientoService.modificar(archivo, nombre, descripcion, email, username, id);
		return "redirect:/";
	}
	
	@PostMapping("/deshabilitar")
	public String deshabilitar(String id) throws Exception {
		emprendimientoService.baja(id);
		return "redirect:/";
	}
	
	
	
	/* //Estas dos funciones están comentadas porque no van acorde a lo acordado respecto a la funcionalidad de un emprendimiento 
	 * @PostMapping("/delete/{id}")
	public String deleteId(@PathVariable String id) throws Exception {
		emprendimientoService.eliminar(id);
		return "redirect:/";
	}
	@PostMapping("/habilitar")
	public String habilitar(String id) throws Exception {
		emprendimientoService.alta(id);
		return "redirect:/";
	}
	*/
	
}
