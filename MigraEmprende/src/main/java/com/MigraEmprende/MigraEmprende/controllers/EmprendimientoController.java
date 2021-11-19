package com.MigraEmprende.MigraEmprende.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.MigraEmprende.MigraEmprende.entities.Emprendimiento;
import com.MigraEmprende.MigraEmprende.services.EmprendimientoService;
import com.MigraEmprende.MigraEmprende.services.ValidationsService;


@Controller

@RequestMapping("/emprendimiento")
public class EmprendimientoController {
	
	@Autowired
	private EmprendimientoService emprendimientoService;
	@Autowired
	private ValidationsService validation;
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/")   //Este get se usa para las cards de emprendimientos en "entrepreneurship-all"
	public String lista(ModelMap modelo) {
		List<Emprendimiento> listarEmprendimientos = emprendimientoService.listarEmprendimientos();
		
		modelo.addAttribute("todosEmprendimientos", listarEmprendimientos); //entre comillas ES UNA VARIABLE que va al front y lleva consigo la lista que puse despues de la coma
		
  	//la variable "todosEmprendimientos" es para ITERAR en th:each 
	//y mostrar atributos como nombre, foto, etc de cada emprendimiento
		
		return "entrepreneurship-all";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/form") //Devuelve el formulario para crear y modificar un emprendimiento
	public String form() {
		return "entrepreneurship-form";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/crear") //Este get se usa para enviar al form del front un nuevo emprendimiento y pedirle los datos
	public String crearEmprendimiento(Model model) {
		try {			
			
			
			model.addAttribute("negocito", new Emprendimiento());			
			
			
		} catch (Exception e) {
			System.out.println("EmprendimientoController: Error al crear emprendimiento");
			e.printStackTrace();
		}
		
		return "/entrepreneurship-form"; //retornaba el form antes, lo cambie para ver si funciona el boton (y si)
	}
	
	
	@PostMapping("/emprendimiento-crear") // Envía los datos del formulario acá para crear el emprendimiento
	public String crear(MultipartFile archivo, String nombre, String descripcion, String email, String username, String address, String phone, String facebook, String instagram) throws Exception {
		emprendimientoService.crear(archivo, nombre, descripcion, email, username, address, phone, facebook, instagram);
		return "redirect:/";
	}
	
	
	// fin crear
	

	
	
	
	

@GetMapping("/{id}") //Devuelve un único emprendimiento
public String id(@PathVariable String id) {
	return "emprendimiento";
}


@PostMapping("/modificar/{id}") // Envía los datos del formulario acá para editar un emprendimiento
public String modificarId(@PathVariable String id, MultipartFile archivo, String nombre, String descripcion, String email, String username, String address, String phone, String facebook, String instagram) throws Exception {
	emprendimientoService.modificar(archivo, nombre, descripcion, email, username, id);
	return "redirect:/";
}

@GetMapping("/deshabilitar/{id}")
public String deshabilitar(@PathVariable String id) throws Exception {
	emprendimientoService.baja(id);
	return "redirect:/";
}




/* //Estas dos funciones están comentadas porque no van acorde a lo acordado respecto a la funcionalidad de un emprendimiento 
 * @PostMapping("/delete/{id}")
public String deleteId(@PathVariable String id) throws Exception {
	emprendimientoService.eliminar(id);
	return "redirect:/";
}
@PostMapping("/habilitar")
public String habilitar(String id) throws Exception {
	emprendimientoService.alta(id);
	return "redirect:/";
}
*/
	
	
	
}
