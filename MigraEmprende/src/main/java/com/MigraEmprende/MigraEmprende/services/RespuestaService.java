package com.MigraEmprende.MigraEmprende.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MigraEmprende.MigraEmprende.entities.Comentario;
import com.MigraEmprende.MigraEmprende.entities.Respuesta;
import com.MigraEmprende.MigraEmprende.entities.Usuario;
import com.MigraEmprende.MigraEmprende.repositories.RespuestaRepository;

@Service
public class RespuestaService {

	@Autowired
	private RespuestaRepository respuestaRepository;

	@Autowired
	private ValidationsService validationsService;

	@Transactional
	public void crearRespuesta(String contenido, Usuario usuario, Comentario comentario) throws Exception {

		try {

			// validations

			validationsService.ValidarContenido(contenido);
			validationsService.ValidarUsuario(usuario);
			validationsService.ValidarComentario(comentario);

			// create

			Respuesta entidad = new Respuesta();

			entidad.setContenido(contenido);
			entidad.setFecha(new Date());
			entidad.setAlta(true);
			entidad.setComentario(comentario);
			entidad.setUsuario(usuario);

			respuestaRepository.save(entidad);

		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public List<Respuesta> listarRespuestas() throws Exception {
		try {
			List<Respuesta> listaRespuestas;
			listaRespuestas = respuestaRepository.findAll();
			return listaRespuestas;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void borrarRespuesta(String id) throws Exception {
		try {
			
			// validations
			
			validationsService.ValidarId(id);
			validationsService.ValidarIdExiste(id);
			
			// method
			
			Respuesta entidad = respuestaRepository.findById(id).get();
			respuestaRepository.delete(entidad);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public Respuesta bajaRespuesta(String id) throws Exception {
		try {
			
			// validations
			
			validationsService.ValidarId(id);
			validationsService.ValidarIdExiste(id);
			
			// method
			
			Respuesta entidad = respuestaRepository.findById(id).get();
			entidad.setAlta(false);
			return respuestaRepository.save(entidad);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public Respuesta altaRespuesta(String id) throws Exception {
		try {
			
			// validations
			
			validationsService.ValidarId(id);
			validationsService.ValidarIdExiste(id);
			
			// method
			
			Respuesta entidad = respuestaRepository.findById(id).get();
			entidad.setAlta(true);
			return respuestaRepository.save(entidad);
		} catch (Exception e) {
			throw e;
		}
	}

}
