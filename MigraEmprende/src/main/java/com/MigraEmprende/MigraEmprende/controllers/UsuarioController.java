package com.MigraEmprende.MigraEmprende.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.MigraEmprende.MigraEmprende.entities.Usuario;
import com.MigraEmprende.MigraEmprende.repositories.UsuarioRepository;
import com.MigraEmprende.MigraEmprende.services.UsuarioService;

@Controller
@RequestMapping("/user")         
public class UsuarioController {

   @Autowired
   private UsuarioService usuarioService;
   @Autowired
   private UsuarioRepository usuarioRepository;	
    
   @GetMapping("/") // devuelve el perfil del usuario
	public String indexProfile(@PathVariable String id) { 
	   return "redirect:/profile/{id}";
	}
   
  @GetMapping("/register") // Devuelve el form para registrarse y/o cambiar datos
  public String register (ModelMap model) {
	  model.put("tipoForm", "register");
	  model.put("usuario", new Usuario()); // nueva linea
	  return "user-form";
  }
  
  @GetMapping("/modificar/{id}")
  public String modificar (ModelMap model, @PathVariable String id) {
	  model.put("tipoForm", "modificar/" + id);
	  model.put("usuario", usuarioRepository.getById(id));
	  return "user-form";
  }
   
   @GetMapping("/login") //Devuelve el form para loguearse
	public String login() {
		return "login";
	}
   
   //prueba para borrar
   @GetMapping("/userform")
	public String userform() {
		return "user-form";
	}
   
   @GetMapping("/user") // PERFIL DE USUARIO, DEVUELVE LOS DATOS DEL USUARIO LOGEADO(LOGIN)
	public String userProfile(Model model, Principal principal) throws Exception {
       try {
		Usuario user = usuarioService.findByUsername(principal.getName());
		model.addAttribute("userName", user.getUsername());
		model.addAttribute("nombreyapellido", user.getNombreYApellido());
		model.addAttribute("email", user.getEmail());
		
		return "profile";
       }catch(Exception e) {
       	throw e;
       }
   }
    
   @GetMapping("/profile/{id}") // Devuelve el perfil del usuario
   public String profile() {
	   return "profile";
   }
     
   
   @PostMapping("/register") // Envía los datos del formulario de registro acá
	public String register(MultipartFile archivo, String name, String user, String email, String pass) throws Exception {				
	    usuarioService.crear(archivo, name, user, email, pass);
		return "redirect:/";
	}
   
   
   @PostMapping("/modificar/{id}") // Envía los datos del formulario de edicion acá
	public String modificar(@PathVariable String id, MultipartFile archivo, String name, String user, String email, String pass) throws Exception {
	   String password = usuarioRepository.getById(id).getPassword();
	   System.out.println("Valores actuales:" + " " + name + " " + user + " " + email + " " + password);
	   usuarioService.modificar(archivo, name, user, email, password, id);
		return "redirect:/";
	}
   
   
   @PostMapping("/delete/{id}") // Elimina un usuario
	public String delete(@PathVariable String id) throws Exception { 
	   usuarioRepository.deleteById(id);
		return "redirect:/";
	}
   
   
   @PostMapping("/deshabilitar/{id}")  // Se da de baja a un usuario
   public String deshabilitar(@PathVariable String id) throws Exception{
	   usuarioService.baja(id);
	   return "redirect:/";
   }     
   
}
   