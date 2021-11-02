package com.MigraEmprende.MigraEmprende.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String titulo;
	private String contenido;
	private String email;
	private Date fecha;
	private Boolean alta;

	@OneToOne
	private Usuario usuario;

	public Comentario() {

	}

	public Comentario(String id, String titulo, String contenido, String email, Date fecha, Boolean alta,
			Usuario usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.contenido = contenido;
		this.email = email;
		this.fecha = fecha;
		this.alta = alta;
		this.usuario = usuario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean getAlta() {
		return alta;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", titulo=" + titulo + ", contenido=" + contenido + ", email=" + email
				+ ", fecha=" + fecha + ", alta=" + alta + ", usuario=" + usuario + "]";
	}

}
