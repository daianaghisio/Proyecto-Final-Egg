package com.MigraEmprende.MigraEmprende.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.MigraEmprende.MigraEmprende.repositories.UsuarioRepository;
import com.MigraEmprende.MigraEmprende.services.UsuarioService;

@Controller
@RequestMapping("/user")         
public class UsuarioController {

   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private UsuarioRepository usuarioRepository;
	
   
   @GetMapping("/")
	public String indexProfile(@PathVariable String id) { 
	   return "redirect:/profile/{id}";
	}
   
  
   @GetMapping("/form")
	public String form() {
		return "formUser";
	}
   
    
   @GetMapping("/profile/{id}")
   public String profile() {
	   return "profile";
   }
     
   
   @PostMapping("/crear")
	public String crear(MultipartFile archivo, String nombreYApellido, String username, String password, String email) throws Exception {
		usuarioService.crear(archivo, nombreYApellido, username, password, email);
		return "redirect:/";
	}
   
   
   @PostMapping("/modificar/{id}")
	public String modificar(@PathVariable String id, MultipartFile archivo, String NombreYApellido, String username, String email, String contrasenia) throws Exception {
		usuarioService.modificar(archivo, NombreYApellido, username, email, contrasenia, id);
		return "redirect:/";
	}
   
   
   @PostMapping("/delete/{id}")
	public String delete(@PathVariable String id) throws Exception { 
	   usuarioRepository.deleteById(id);
		return "redirect:/";
	}
   
   
   @PostMapping("/deshabilitar/{id}")
   public String deshabilitar(@PathVariable String id) throws Exception{
	   usuarioService.baja(id);
	   return "redirect:/";
   }
     
   
}
   