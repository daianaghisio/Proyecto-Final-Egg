package com.MigraEmprende.MigraEmprende.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MigraEmprende.MigraEmprende.entities.Comentario;
import com.MigraEmprende.MigraEmprende.entities.Usuario;
import com.MigraEmprende.MigraEmprende.repositories.ComentarioRepository;

@Service
public class ComentarioService {
	
	// INSTANCIAMOS EL REPOSITORIO
	@Autowired
	private ComentarioRepository comentRepository;
	
	// CREAR COMENTARIO
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
	
	// LISTAR TODOS LOS COMENTARIOS
	@Transactional(readOnly=true)
	public List<Comentario> listarComentarios() {
		try {
			List<Comentario> listaComentarios;
			listaComentarios = comentRepository.findAll();
			return listaComentarios;
		} catch (Exception e) {
			System.out.println("No se pudo listar los comentarios");
			throw e;
		}	
	}
	
	// BORRAR COMENTARIO
	@Transactional
	public void borrarComentario(String id) {
		try {
			Comentario entidad = comentRepository.findById(id).get();
			comentRepository.delete(entidad);
		} catch (Exception e) {
			System.out.println("No se pudo eliminar el cometario");
		}
	}
	
	// BAJA COMENTARIO
	public Comentario bajaComentario(String id) {
		try {
			Comentario entidad = comentRepository.findById(id).get();
			entidad.setAlta(false);
			return comentRepository.save(entidad);
		} catch (Exception e) {
			System.out.println("No se pudo dar de baja el comentario");
			throw e;
		}
	}
	
	// ALTA COMENTARIO
	public Comentario altaComentario(String id) {
		try {
			Comentario entidad = comentRepository.findById(id).get();
			entidad.setAlta(true);
			return comentRepository.save(entidad);
		} catch (Exception e) {
			System.out.println("No se pudo dar de alta el comentario");
			throw e;
		}
	}
	
	// LISTAR COMENTARIOS POR EMAIL 
	// FALTA QUERY EN REPOSITORY
	/*
	public List<Comentario> listarComentariosId(String email){
		try {
			List<Comentario> listaComenatario = comentRepository.
			return listaComentario;
		} catch (Exception e) {
			System.out.println("No se pudo listar los comentarios por id");
			throw e;
		}
	}
	*/
	
	// LISTAR COMENTARIOS POR USUARIO
	// FALTA QUERY EN REPOSITORY
	/*
	public List<Comentario> listarComentariosUsuario(Usuario usuario){
		try {
			List<Comentario> listaComentario = comentRepository.
			return listaComentario;
		} catch (Exception e) {
			System.out.println("No se pudo listar los comentarios por Usuario");
			throw e;
		}
	}
	*/
}
