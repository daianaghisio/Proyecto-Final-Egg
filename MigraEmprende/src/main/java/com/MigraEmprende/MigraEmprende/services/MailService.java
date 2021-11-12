package com.MigraEmprende.MigraEmprende.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ValidationsService validationsServices;

	public void enviarMail(String nombre, String email, String mensaje) throws Exception {

		try {

			// validations

			validationsServices.ValidarNombre(nombre);
			validationsServices.ValidarEmail(email);
			validationsServices.ValidarMensaje(mensaje);

			// method

			SimpleMailMessage message1 = new SimpleMailMessage();
			message1.setTo("nosepruebanoseprueba@gmail.com");
			message1.setSubject("Consulta via Web Recibida");
			message1.setText(nombre + ", cuyo mail es " + email + ", envió el siguiente mensaje: " + mensaje);

			javaMailSender.send(message1);

			SimpleMailMessage message2 = new SimpleMailMessage();
			message2.setTo(email);
			message2.setSubject("Has enviado una consulta a MigraEmprende");
			message2.setText(
					"Gracias por su consulta, le responderemos a la brevedad.\nLos datos que usted proporcionó fueron:\n\n"
							+ "Nombre: " + nombre + "\n\n" + "Email: " + email + "\n\n" + "Mensaje: " + mensaje
							+ "\n\n\n\n" + "MigraEmprende - Rosario, Santa Fé, Argentina");

			javaMailSender.send(message2);

		} catch (Exception e) {
			throw e;
		}

	}

}
