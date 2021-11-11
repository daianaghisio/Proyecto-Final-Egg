package com.MigraEmprende.MigraEmprende.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.MigraEmprende.MigraEmprende.entities.Emprendimiento;
import com.MigraEmprende.MigraEmprende.entities.Foto;
import com.MigraEmprende.MigraEmprende.repositories.EmprendimientoRepository;
import com.MigraEmprende.MigraEmprende.repositories.UsuarioRepository;


@Service
public class EmprendimientoService {
	
	@Autowired
	private EmprendimientoRepository emprendimientoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ValidationsService validationsService;
	
	@Autowired
	private FotoService fotoService;

	// // // Métodos CRUD
	public void crear(MultipartFile archivo, String nombre, String descripcion, String email, String username) throws Exception {
		try {

			// validations

			validationsService.ValidarNombre(nombre);
			validationsService.ValidarDescripcion(descripcion);
			validationsService.ValidarEmail(email);
			validationsService.ValidarUsername(username);

			// create

			Emprendimiento emprendimiento = new Emprendimiento(); // Al crear un nuevo emprendimiento el UUID le da una
																	// ID automáticamente
			emprendimiento.setNombre(nombre); // Setteamos los datos traidos por parámetro
			emprendimiento.setDescripcion(descripcion);
			emprendimiento.setEmail(email);
			emprendimiento.setUsuario(usuarioRepository.retornarUsuarioPorUsername(username)); // Nos llega un username
																								// por cadena y lo
																								// enviamos a la query
																								// para que lo busque
																								// por usuario y lo
																								// devuelva como objeto
																								// entero
			emprendimiento.setAlta(true); // Setteamos el alta en true

			Foto foto = fotoService.guardar(archivo);
			emprendimiento.setFoto(foto);
			
			
			emprendimientoRepository.save(emprendimiento); // Guardamos los cambios
		} catch (Exception e) {
			System.out.println("Error-EmprendimientoService: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void modificar(MultipartFile archivo, String nombre, String descripcion, String email, String username, String id)
			throws Exception {
		try {

			// validations

			validationsService.ValidarNombre(nombre);
			validationsService.ValidarDescripcion(descripcion);
			validationsService.ValidarEmail(email);
			validationsService.ValidarUsername(username);
			validationsService.ValidarId(id);
			validationsService.ValidarIdEmprendimientoExiste(id);

			// mod

			Optional<Emprendimiento> respuesta = emprendimientoRepository.findById(id); // Se crea el optional, sirve
																						// para el siguiente paso
			if (respuesta.isPresent()) { // Si no es nula la id encontrada entonces procede a settear los datos
				Emprendimiento emprendimiento = respuesta.get(); //
				emprendimiento.setNombre(nombre); // Setteamos los datos traidos por parámetro
				emprendimiento.setDescripcion(descripcion);
				emprendimiento.setEmail(email);
				emprendimiento.setUsuario(usuarioRepository.retornarUsuarioPorUsername(username)); // Nos llega un
																									// username por
																									// cadena y lo
																									// enviamos a la
																									// query para que lo
																									// busque por
																									// usuario y lo
																									// devuelva como
																									// objeto entero
				String idFoto = null;
				if(emprendimiento.getFoto() != null) {
					idFoto = emprendimiento.getFoto().getId();
				}
				Foto foto = fotoService.actualizar(idFoto, archivo);
				emprendimiento.setFoto(foto);

				emprendimientoRepository.save(emprendimiento); // Guardamos los cambios
			} else { // Si la id es nula, entonces tira error
				throw new Exception("No se ha encontrado ningún emprendimiento con esa id");
			}

		} catch (Exception e) {
			System.out.println("Error-EmprendimientoService: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void baja(String id) throws Exception {

		try {

			// validations
			validationsService.ValidarId(id);
			validationsService.ValidarIdEmprendimientoExiste(id);

			// method
			Optional<Emprendimiento> respuesta = emprendimientoRepository.findById(id);
			if (respuesta.isPresent()) {
				Emprendimiento emprendimiento = respuesta.get();
				emprendimiento.setAlta(false);

				emprendimientoRepository.save(emprendimiento);
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public void alta(String id) throws Exception {

		try {

			// validations
			validationsService.ValidarId(id);
			validationsService.ValidarIdEmprendimientoExiste(id);

			// method
			Optional<Emprendimiento> respuesta = emprendimientoRepository.findById(id);
			if (respuesta.isPresent()) {
				Emprendimiento emprendimiento = respuesta.get();
				emprendimiento.setAlta(true);

				emprendimientoRepository.save(emprendimiento);
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public void eliminar(String id) throws Exception {

		try {

			// validations
			validationsService.ValidarId(id);
			validationsService.ValidarIdEmprendimientoExiste(id);

			// method
			Optional<Emprendimiento> respuesta = emprendimientoRepository.findById(id);
			if (respuesta.isPresent()) {
				Emprendimiento emprendimiento = respuesta.get();
				emprendimientoRepository.delete(emprendimiento);
				emprendimientoRepository.save(emprendimiento);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	// // // Métodos Buscar

	public Emprendimiento buscarPorId(String id) throws Exception {

		try {

			// validations

			validationsService.ValidarId(id);
			validationsService.ValidarIdEmprendimientoExiste(id);

			// method

			return emprendimientoRepository.getById(id);

		} catch (Exception e) {
			throw e;
		}

	}
	
	

	//LISTAR EMPRENDIMIENTOS
	@Transactional(readOnly=true) //Se usa por buenas practicas aclarando entre parentesis que no es una modificacion a la BBDD
	public List<Emprendimiento> listarEmprendimientos(){
		return emprendimientoRepository.findAll();
	}

}
