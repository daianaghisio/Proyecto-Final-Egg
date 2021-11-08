package com.MigraEmprende.MigraEmprende.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MigraEmprende.MigraEmprende.entities.Emprendimiento;
import com.MigraEmprende.MigraEmprende.entities.Usuario;
import com.MigraEmprende.MigraEmprende.services.EmprendimientoService;
import com.MigraEmprende.MigraEmprende.services.UsuarioService;

@Controller
@RequestMapping("/foto")
public class FotoController {

	@Autowired
	private UsuarioService usuarioService;
	private EmprendimientoService emprendimientoService;
	
	
	
	@GetMapping("/emprendimiento")
	public ResponseEntity<byte[]> fotoEmprendimiento(@RequestParam String id) throws Exception {
		
	try {
		Emprendimiento emprendimiento = emprendimientoService.buscarPorId(id);
		
		if(emprendimiento.getFoto() == null) {
			throw new Exception("El emprendimiento no tiene una foto asignada.");
		}
		
		byte[] foto = emprendimiento.getFoto().getContenido();
	
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<>(foto, headers, HttpStatus.OK); 
	} catch (Exception e) {
		System.out.println("FotoController: No se encontro el id de la foto");
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
	}
	
	
	
	
	@GetMapping("/usuario")
	public ResponseEntity<byte[]> fotoUsuario(@RequestParam String id) throws Exception {

	try {
		Usuario usuario = usuarioService.buscarPorId(id);
		
		if(usuario.getFoto() == null) {
			throw new Exception("El usuario no tiene una foto asignada.");
		}
		
		byte[] foto = usuario.getFoto().getContenido();
	
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<>(foto, headers, HttpStatus.OK); 
	} catch (Exception e) {
		System.out.println("FotoController: No se encontro el id de la foto");
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
		
	}
}