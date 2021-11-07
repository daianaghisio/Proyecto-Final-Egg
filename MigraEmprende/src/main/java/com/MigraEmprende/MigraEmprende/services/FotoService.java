package com.MigraEmprende.MigraEmprende.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.MigraEmprende.MigraEmprende.entities.Foto;
import com.MigraEmprende.MigraEmprende.repositories.FotoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository repo;
	
	public Foto guardar(MultipartFile archivo) throws Exception{
		if(archivo != null) {
			try {
			
		     	Foto foto = new Foto(); 
			    foto.setMime(archivo.getContentType()); 
			    foto.setNombre(archivo.getName());
			    foto.setContenido(archivo.getBytes()); 
			    
			   
			    return repo.save(foto); 
			
			} catch(Exception e) {
				System.err.println(e.getMessage()); 
			}
		}
			return null;
		}
	
	
	public Foto actualizar(String idFoto, MultipartFile archivo) throws Exception{
		if(archivo != null) {
			try {
			
		     	Foto foto = new Foto(); 
		     	
		     	if(idFoto != null) { 		     		
		     		Optional<Foto> respuesta = repo.findById(idFoto);
		     		if(respuesta.isPresent()) {
		     			foto = respuesta.get();
		     	    }
		     	}
		     	
			    foto.setMime(archivo.getContentType()); 
			    foto.setNombre(archivo.getName()); 
			    foto.setContenido(archivo.getBytes());
			
			    return repo.save(foto); 
			
			} catch(Exception e) {
				System.err.println(e.getMessage()); 
			}
		}
			return null;
		}
	
	
	}
