package com.MigraEmprende.MigraEmprende.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.MigraEmprende.MigraEmprende.entities.Usuario;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ValidationsService validationsServices;
	
	@Autowired
	private UsuarioService usuarioService;

	public void enviarMail(String nombre, String email, String mensaje) throws Exception {

		try {

			// validations

			validationsServices.ValidarNombre(nombre);
			validationsServices.ValidarEmail(email);
			validationsServices.ValidarMensaje(mensaje);

			// method

			SimpleMailMessage message1 = new SimpleMailMessage();
			message1.setFrom("nosepruebanoseprueba@gmail.com");
			message1.setTo("nosepruebanoseprueba@gmail.com");
			message1.setSubject("Consulta via Web Recibida");
			message1.setText(nombre + ", cuyo mail es " + email + ", envió el siguiente mensaje: " + mensaje);

			mailSender.send(message1);

			SimpleMailMessage message2 = new SimpleMailMessage();
			message2.setFrom("nosepruebanoseprueba@gmail.com");
			message2.setTo(email);
			message2.setSubject("Has enviado una consulta a MigraEmprende");
			message2.setText(
					"Gracias por su consulta, le responderemos a la brevedad.\nLos datos que usted proporcionó fueron:\n\n"
							+ "Nombre: " + nombre + "\n\n" + "Email: " + email + "\n\n" + "Mensaje: " + mensaje
							+ "\n\n\n\n" + "MigraEmprende - Rosario, Santa Fé, Argentina");

			mailSender.send(message2);

		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void resetPass(String email) throws Exception{
		try {
			validationsServices.ValidarEmail(email);
			
			Usuario usuario = usuarioService.buscarPorEmail(email);
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("nosepruebanoseprueba@gmail.com");
			message.setTo(email);
			message.setSubject("Recuperación de contraseña - MigraEmprende");
			message.setText(
					"Usted inició el proceso para recuperar su contraseña. En caso contrario, ignore este mail\n"
							+ "Los datos de su cuenta:\n\n"
							+ "Nombre de usuario: " + usuario.getUsername() + "\n\n" 
							+ "Ingrese al siguiente link para cambiar su contraseña:\n\n"
							+ "localhost:8080/user/resetpassword/" + usuario.getId()
							+ "\n\n\n\n" + "MigraEmprende - Rosario, Santa Fé, Argentina");

			mailSender.send(message);
		} catch (Exception e) {
			throw e;
		}
	}
}
