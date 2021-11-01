package com.MigraEmprende.MigraEmprende.services;

import java.sql.Date;
// import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MigraEmprende.MigraEmprende.entities.Comentario;
import com.MigraEmprende.MigraEmprende.entities.Usuario;

@Service
public class ComentarioService {
	/*
	@Autowired
	private ComentarioRepository comentRepository;
	*/
	
	@Transactional
	public Comentario crearComentario(String titulo, String contenido, String email, Date fecha, Usuario usuario) {
		
		try {
			Comentario entidad = new Comentario();
			
			entidad.setTitulo(titulo);
			entidad.setContenido(contenido);
			entidad.setEmail(email);
			entidad.setFecha(fecha);
			entidad.setUsuario(usuario);
			entidad.setAlta(true);
			
			return entidad;
		} catch (Exception e) {
			System.out.println("No se pudo crear el comentario");
			throw e;
		}
		
	}
	
	/* LISTAR TODOS LOS COMENTARIOS
	@Transactional(readOnly=true)
	public List<Comentario> listarComentarios() {
		List<Comentario> listaCentarios;
		try {
			listaComentarios = comentRepository.findAll();
			return listaComentarios;
		} catch (Exception e) {
			System.out.println("No se pudo listar los comentarios");
			throw e;
		}
		
	}
	*/
	
	
}
