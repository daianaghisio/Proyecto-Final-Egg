package com.MigraEmprende.MigraEmprende.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.MigraEmprende.MigraEmprende.entities.Emprendimiento;
import com.MigraEmprende.MigraEmprende.repositories.EmprendimientoRepository;
import com.MigraEmprende.MigraEmprende.repositories.UsuarioRepository;

public class EmprendimientoService {

	@Autowired
	private EmprendimientoRepository emprendimientoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	// // // Métodos CRUD
	public void crear(String nombre, String descripcion, String email, String username) throws Exception {
		try {
			Emprendimiento emprendimiento = new Emprendimiento(); // Al crear un nuevo emprendimiento el UUID le da una ID automáticamente
			emprendimiento.setNombre(nombre); // Setteamos los datos traidos por parámetro
			emprendimiento.setDescripcion(descripcion);
			emprendimiento.setEmail(email);
			emprendimiento.setUsuario( usuarioRepository.retornarUsuarioPorUsername(username) ); // Nos llega un username por cadena y lo enviamos a la query para que lo busque por usuario y lo devuelva como objeto entero			
			emprendimiento.setAlta(true); // Setteamos el alta en true
			
			emprendimientoRepository.save(emprendimiento); // Guardamos los cambios
		}catch(Exception e) {
			System.out.println("Error-EmprendimientoService: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void modificar(String nombre, String descripcion, String email, String username, String id) throws Exception {
		try {
			Optional<Emprendimiento> respuesta = emprendimientoRepository.findById(id); // Se crea el optional, sirve para el siguiente paso 
			if(respuesta.isPresent()) { // Si no es nula la id encontrada entonces procede a settear los datos
				Emprendimiento emprendimiento = respuesta.get(); // 
				emprendimiento.setNombre(nombre); // Setteamos los datos traidos por parámetro
				emprendimiento.setDescripcion(descripcion);
				emprendimiento.setEmail(email);
				emprendimiento.setUsuario( usuarioRepository.retornarUsuarioPorUsername(username) ); // Nos llega un username por cadena y lo enviamos a la query para que lo busque por usuario y lo devuelva como objeto entero
				
				
				emprendimientoRepository.save(emprendimiento); // Guardamos los cambios
			}else { // Si la id es nula, entonces tira error
				throw new Exception("No se ha encontrado ningún emprendimiento con esa id");				
			}
			
		}catch(Exception e) {
			System.out.println("Error-EmprendimientoService: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void baja(String id) throws Exception{
		Optional<Emprendimiento> respuesta = emprendimientoRepository.findById(id);
		if(respuesta.isPresent()) {
			Emprendimiento emprendimiento = respuesta.get();
			emprendimiento.setAlta(false);
			
			emprendimientoRepository.save(emprendimiento);
		}else {
			throw new Exception("No se ha encontrado ningún emprendimiento con esa id.");
		}			
	}
	
	public void alta(String id) throws Exception {
		Optional<Emprendimiento> respuesta = emprendimientoRepository.findById(id);
		if(respuesta.isPresent()) {
			Emprendimiento emprendimiento = respuesta.get();
			emprendimiento.setAlta(true);
			
			emprendimientoRepository.save(emprendimiento);
		}else {
			throw new Exception("No se ha encontrado ningún emprendimiento con esa id.");
		}			
	}
	
	public void eliminar(String id) throws Exception {
		Optional<Emprendimiento> respuesta = emprendimientoRepository.findById(id);
		if(respuesta.isPresent()) {
			Emprendimiento emprendimiento = respuesta.get();
			emprendimientoRepository.delete(emprendimiento);			
			emprendimientoRepository.save(emprendimiento);
		}else {
			throw new Exception("No se ha encontrado ningún emprendimiento con esa id.");
		}		
	}
	// // // Métodos CRUD	
	
	// // // Métodos Buscar
	public Emprendimiento buscarPorId(String id) {
		return emprendimientoRepository.getById(id);
	}		
	// // // Métodos Buscar
	
}
