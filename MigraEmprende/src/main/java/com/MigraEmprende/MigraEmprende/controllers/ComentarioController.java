package com.MigraEmprende.MigraEmprende.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MigraEmprende.MigraEmprende.entities.Usuario;
import com.MigraEmprende.MigraEmprende.services.ComentarioService;

@Controller
@RequestMapping("/foro")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@GetMapping("/") // Devuelve todo el foro
	public String index(ModelMap modelo) throws Exception {
		modelo.addAttribute("listaComentario", comentarioService.listarComentarios());
		
		return "comments-section";
	}

	@GetMapping("/{id}") // Devuelve un único comentario con cada respuesta
	public String id(ModelMap modelo, @PathVariable String id) throws Exception {

		modelo.addAttribute("id", id);
		return "topic";
	}
	
	@GetMapping("/crear") // Devuelve un formulario para crear un comentario
	public String crear() {
		return "topic-form";
	}
	

	@PostMapping("/crear") // Manda los datos del formulario y lo redirecciona a el foro
	public String crear(ModelMap modelo, @RequestParam String titulo, @RequestParam String contenido,
			@RequestParam Usuario usuario) throws Exception {
		try {
			comentarioService.crearComentario(titulo, contenido, usuario);
			modelo.put("exito", "Comentario enviado!");
			return "redirect:/";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "redirect:/";
		}
	}

	@PostMapping("/delete/{id}") // 
	public String deleteId(ModelMap modelo, @PathVariable String id) throws Exception {

		try {
			comentarioService.bajaComentario(id);
			modelo.put("exito", "Comentario borrado!");
			return "redirect:/";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "redirect:/";
		}
	}

}
