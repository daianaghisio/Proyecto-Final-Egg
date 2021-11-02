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
	ComentarioService comentarioService;

	@GetMapping("/")
	public String index() throws Exception {
		return "foro";
	}

	@GetMapping("/{id}")
	public String id(@PathVariable String id) {
		return "foroComentario";
	}

	@PostMapping("/crear")
	public String crear(ModelMap modelo, @RequestParam String titulo, @RequestParam String contenido,
			@RequestParam String email, @RequestParam Usuario usuario) {
		try {
			// VER EMAIL Y USUARIO
			comentarioService.crearComentario(titulo, contenido, email, usuario);
			modelo.put("exito", "Comentario enviado!");
			return "redirect:/";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "redirect:/";
		}
	}

	@PostMapping("/delete/{id}")
	public String deleteId(@PathVariable String id) {
		return "redirect:/";
	}

}
