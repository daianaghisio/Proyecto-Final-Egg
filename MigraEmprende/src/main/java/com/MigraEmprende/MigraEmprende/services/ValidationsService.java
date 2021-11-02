package com.MigraEmprende.MigraEmprende.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.MigraEmprende.MigraEmprende.entities.Respuesta;

@Service
public class ValidationsService {
	
	public void ValidarNombreYApellido(String nombreYApellido) throws Exception{
		if(nombreYApellido == null || nombreYApellido.isEmpty()) {
			throw new Exception("El nombre y apellido no puede ser nulo.");
		}
	}
	
	public void ValidarUsername(String username) throws Exception{
		if(username == null || username.isEmpty()) {
			throw new Exception("El username no puede ser nulo.");
		}
	}
	
	public void ValidarPassword(String password) throws Exception{
		if(password == null || password.isEmpty()) {
			throw new Exception("El password no puede ser nulo.");
		}
	}
	
	public void ValidarEmail(String email) throws Exception{
		if(email == null || email.isEmpty()) {
			throw new Exception("El email no puede ser nulo.");
		}
	}
	
	public void ValidarNombre(String nombre) throws Exception{
		if(nombre == null || nombre.isEmpty()) {
			throw new Exception("El nombre no puede ser nulo.");
		}
	}
	
	public void ValidarDescripcion(String descripcion) throws Exception{
		if(descripcion == null || descripcion.isEmpty()) {
			throw new Exception("La descripcion no puede ser nula.");
		}
	}
	
	public void ValidarTitulo(String titulo) throws Exception{
		if(titulo == null || titulo.isEmpty()) {
			throw new Exception("El titulo no puede ser nulo.");
		}
	}
	
	public void ValidarContenido(String contenido) throws Exception{
		if(contenido == null || contenido.isEmpty()) {
			throw new Exception("El contenido no puede ser nulo.");
		}
	}	
	
	public void ValidarListaRespuesta(List<Respuesta> respuestas) throws Exception {
		if(respuestas.isEmpty()) { // En caso de que la lista esté vacía
			throw new Exception("La lista de respuestas no puede ser nula.");
		}
		for(int i = 0; i < respuestas.size() ; i++) {// verifica cada punto de la lista
			if( respuestas.get(i) == null) {
				throw new Exception("La respuesta no puede ser nula.");
			}
		}
			
		}
}
