package com.MigraEmprende.MigraEmprende.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MigraEmprende.MigraEmprende.services.ComentarioService;

@Controller
@RequestMapping("/foro")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;
/*
	@GetMapping("/")
	public String index() throws Exception {
		return "foro";
	}

	@GetMapping("/{id}")
	public String id(ModelMap modelo, @PathVariable String id) throws Exception {

		modelo.addAttribute("id", id);
		return "foroComentario";
	}

	@PostMapping("/crear")
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

	@PostMapping("/delete/{id}")
	public String deleteId(ModelMap modelo, @PathVariable String id) throws Exception {

		try {
			comentarioService.borrarComentario(id);
			modelo.put("exito", "Comentario borrado!");
			return "redirect:/";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "redirect:/";
		}
	}*/

}
