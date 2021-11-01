package com.MigraEmprende.MigraEmprende.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MigraEmprende.MigraEmprende.entities.Usuario;
import com.MigraEmprende.MigraEmprende.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// // // Métodos CRUD
	public void crear(String NombreYApellido, String username, String email, String contrasenia) throws Exception {
		try {
			Usuario usuario = new Usuario(); // Al crear un nuevo usuario el UUID le da una ID automáticamente
			usuario.setNombreYApellido(NombreYApellido); // Setteamos los datos traidos por parámetro
			usuario.setUsername(username);
			usuario.setPassword(contrasenia);
			usuario.setEmail(email);
			usuario.setAlta(true); // Setteamos el alta en true
			
			usuarioRepository.save(usuario); // Guardamos los cambios
		}catch(Exception e) {
			System.out.println("Error-UsuarioService: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void modificar(String NombreYApellido, String username, String email, String contrasenia, String id) throws Exception {
		try {
			Optional<Usuario> respuesta = usuarioRepository.findById(id); // Se crea el optional, sirve para el siguiente paso 
			if(respuesta.isPresent()) { // Si no es nula la id encontrada entonces procede a settear los datos
				Usuario usuario = respuesta.get(); // 
				usuario.setNombreYApellido(NombreYApellido); // Setteamos los datos traidos por parámetro
				usuario.setUsername(username);
				usuario.setPassword(contrasenia);
				usuario.setEmail(email);
				usuario.setAlta(true); // Setteamos el alta en true
				
				usuarioRepository.save(usuario); // Guardamos los cambios
			}else { // Si la id es nula, entonces tira error
				throw new Exception("No se ha encontrado ningún usuario con esa id");				
			}
			
		}catch(Exception e) {
			System.out.println("Error-UsuarioService: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void baja(String id) throws Exception{
		Optional<Usuario> respuesta = usuarioRepository.findById(id);
		if(respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			usuario.setAlta(false);
			
			usuarioRepository.save(usuario);
		}else {
			throw new Exception("No se ha encontrado ningún usuario con esa id.");
		}			
	}
	
	public void alta(String id) throws Exception {
		Optional<Usuario> respuesta = usuarioRepository.findById(id);
		if(respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			usuario.setAlta(true);
			
			usuarioRepository.save(usuario);
		}else {
			throw new Exception("No se ha encontrado ningún usuario con esa id.");
		}			
	}
	
	public void eliminar(String id) throws Exception {
		Optional<Usuario> respuesta = usuarioRepository.findById(id);
		if(respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			usuarioRepository.delete(usuario);			
			usuarioRepository.save(usuario);
		}else {
			throw new Exception("No se ha encontrado ningún usuario con esa id.");
		}		
	}
	// // // Métodos CRUD	
	
	// // // Métodos Buscar
	public Usuario buscarPorId(String id) {
		return usuarioRepository.getById(id);
	}
	
	
	// // // Métodos Buscar
	
}
