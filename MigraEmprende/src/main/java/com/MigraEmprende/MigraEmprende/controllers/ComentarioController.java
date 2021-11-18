package com.MigraEmprende.MigraEmprende.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MigraEmprende.MigraEmprende.entities.Respuesta;
import com.MigraEmprende.MigraEmprende.repositories.RespuestaRepository;
import com.MigraEmprende.MigraEmprende.services.ComentarioService;
import com.MigraEmprende.MigraEmprende.services.UsuarioService;

@Controller
@RequestMapping("/foro")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RespuestaRepository respuestaRepository;

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/") // Devuelve todo el foro
	public String index(ModelMap modelo) throws Exception {
		modelo.addAttribute("listaComentario", comentarioService.listarComentariosEnAlta());
		
		return "comments-section";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/{id}") // Devuelve un único comentario con cada respuesta
	public String id(ModelMap modelo, @PathVariable String id) throws Exception {

		modelo.addAttribute("id", id);
		modelo.addAttribute("comentario", comentarioService.retornarComentarioPorId(id));
		modelo.addAttribute("respuestas", comentarioService.retornarRespuestasDeComentarioPorId(id));
		return "topic";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/crear") // Devuelve un formulario para crear un comentario
	public String crear() {
		return "form-comments-topic";
	}
	

	@PostMapping("/crear") // Manda los datos del formulario y lo redirecciona a el foro
	public String crear(ModelMap modelo, @RequestParam String name, @RequestParam String contenido,
			@RequestParam String usuario) throws Exception {
		try {
			comentarioService.crearComentario(name, contenido, usuarioService.buscarPorUsername(usuario));
			modelo.put("exito", "Comentario enviado!");
			return "redirect:/foro/";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "redirect:/foro/";
		}
	}

	@GetMapping("/delete/{id}") // 
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
	
	@PostMapping("/{idComentario}/responder")
	public String responder(String contenido, String username, @PathVariable String idComentario,ModelMap modelo) throws Exception{
		
		
		try {			
			
			comentarioService.crearRespuesta(contenido, usuarioService.buscarPorUsername(username), idComentario);
			modelo.put("exito", "Comentario borrado!");
			return "redirect:/foro/{idComentario}/";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "redirect:/foro/";
		}		
	}
	
	
	@GetMapping("/{idComentario}/{idRta}")
	public String deshabilitarRespuesta(@PathVariable String idComentario, @PathVariable String idRta) throws Exception {
		try {
			Respuesta respuesta = respuestaRepository.getById(idRta);
			respuesta.setAlta(false);
			respuestaRepository.save(respuesta);
		}catch(Exception e) {
			throw new Exception("No se ha encontrado una respuesta con esa id");
		}
		
		return "redirect:/foro/{idComentario}";
	}

}
