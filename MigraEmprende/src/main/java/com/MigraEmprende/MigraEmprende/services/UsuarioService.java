package com.MigraEmprende.MigraEmprende.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.MigraEmprende.MigraEmprende.entities.Foto;
import com.MigraEmprende.MigraEmprende.entities.Usuario;
import com.MigraEmprende.MigraEmprende.repositories.UsuarioRepository;


@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ValidationsService validationsService;
	
	@Autowired
	private FotoService fotoService;

	// // // Métodos CRUD
	@Transactional
	public void crear(MultipartFile archivo, String NombreYApellido, String username, String email, String contrasenia) throws Exception {
		try {

			// validations

			validationsService.ValidarNombreYApellido(NombreYApellido);
			validationsService.ValidarUsername(username);
			validationsService.ValidarEmail(email);
			validationsService.ValidarPassword(contrasenia);

			// create
			//CREAMOS ENCRIPTADOR DE CONTRASEÑAS (LOGIN)
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Usuario usuario = new Usuario(); // Al crear un nuevo usuario el UUID le da una ID automáticamente
			usuario.setNombreYApellido(NombreYApellido); // Setteamos los datos traidos por parámetro
			usuario.setUsername(username);
			usuario.setPassword(encoder.encode(contrasenia)); //CREAMOS EL USUARIO CON LA CONTRASEÑA ENCRIPTADA (LOGIN)
			usuario.setEmail(email);
			usuario.setAlta(true); // Setteamos el alta en true
			usuario.setRol("ROLE_USER"); // PERMISOS DE USUARIO(LOGIN)

			Foto foto = fotoService.guardar(archivo);
			usuario.setFoto(foto);
			
			usuarioRepository.save(usuario); // Guardamos los cambios
		} catch (Exception e) {
			System.out.println("Error-UsuarioService: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public void modificar(MultipartFile archivo, String NombreYApellido, String username, String email, String contrasenia, String id)
			throws Exception {
		try {

			// validations

			validationsService.ValidarNombreYApellido(NombreYApellido);
			validationsService.ValidarUsername(username);
			validationsService.ValidarEmail(email);
			validationsService.ValidarPassword(contrasenia);
			validationsService.ValidarId(id);
			validationsService.ValidarIdUsuarioExiste(id);

			// mod
			Optional<Usuario> respuesta = usuarioRepository.findById(id); // Se crea el optional, sirve para el
																			// siguiente paso
			if (respuesta.isPresent()) { // Si no es nula la id encontrada entonces procede a settear los datos
				Usuario usuario = respuesta.get(); //
				usuario.setNombreYApellido(NombreYApellido); // Setteamos los datos traidos por parámetro
				usuario.setUsername(username);
				usuario.setPassword(contrasenia);
				usuario.setEmail(email);

				String idFoto = null;
				if(usuario.getFoto() != null) {
					idFoto = usuario.getFoto().getId();
				}
				Foto foto = fotoService.actualizar(idFoto, archivo);
				usuario.setFoto(foto);
				
				usuarioRepository.save(usuario); // Guardamos los cambios
			}

		} catch (Exception e) {
			System.out.println("Error-UsuarioService: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public void baja(String id) throws Exception {

		try {

			// validations

			validationsService.ValidarId(id);
			validationsService.ValidarIdUsuarioExiste(id);

			// method
			Optional<Usuario> respuesta = usuarioRepository.findById(id);
			if (respuesta.isPresent()) {
				Usuario usuario = respuesta.get();
				usuario.setAlta(false);

				usuarioRepository.save(usuario);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	public void alta(String id) throws Exception {

		try {

			// validations

			validationsService.ValidarId(id);
			validationsService.ValidarIdUsuarioExiste(id);

			// method
			Optional<Usuario> respuesta = usuarioRepository.findById(id);
			if (respuesta.isPresent()) {
				Usuario usuario = respuesta.get();
				usuario.setAlta(true);

				usuarioRepository.save(usuario);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	public void eliminar(String id) throws Exception {

		try {

			// validations

			validationsService.ValidarId(id);
			validationsService.ValidarIdUsuarioExiste(id);

			// method

			Optional<Usuario> respuesta = usuarioRepository.findById(id);
			if (respuesta.isPresent()) {
				Usuario usuario = respuesta.get();
				usuarioRepository.delete(usuario);
				usuarioRepository.save(usuario);
			}

		} catch (Exception e) {
			throw e;

		}

	}

	// // // Métodos Buscar
	public Usuario buscarPorId(String id) throws Exception {
		try {

			// validations

			validationsService.ValidarId(id);
			validationsService.ValidarIdUsuarioExiste(id);

			// method

			return usuarioRepository.getById(id);

		} catch (Exception e) {
			throw e;
		}

	}

	public Usuario buscarPorEmail(String email) throws Exception {
		try {

			// validations

			validationsService.ValidarEmail(email);

			// method

			return usuarioRepository.retornarUsuarioPorEmail(email);

		} catch (Exception e) {
			throw e;
		}

	}

	public Usuario buscarPorUsername(String username) throws Exception {
		try {

			// validations

			validationsService.ValidarUsername(username);

			// method

			return usuarioRepository.retornarUsuarioPorUsername(username);

		} catch (Exception e) {
			throw e;
		}

	}
	
	// RETORNA USUARIO (LOGIN)
	@Transactional(readOnly=true)
	public Usuario findByUsername(String username) throws Exception{
		try {
			validationsService.ValidarUsername(username);
			return usuarioRepository.findByUsername(username)
					.orElseThrow(()->new Exception("Usuario no encontrado"));
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.retornarUsuarioPorUsername(username);
		if(usuario != null) {
			List<GrantedAuthority> permisos = new ArrayList<>();
			
			GrantedAuthority p1 = new SimpleGrantedAuthority(usuario.getRol());
			permisos.add(p1);

			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			session.setAttribute("usuariosession", usuario);
			
			User user = new User(usuario.getUsername(), usuario.getPassword(), permisos);
			return user;
		}	
		
		return null;
	}
	
	//CHANGE PASS
	@Transactional
	public Usuario changePass(String id, String password) throws Exception{
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			
			Usuario user = buscarPorId(id);
			user.setPassword(encoder.encode(password));
			usuarioRepository.save(user);
			return user;
		} catch (Exception e) {
			throw e;
		}
	}
}
