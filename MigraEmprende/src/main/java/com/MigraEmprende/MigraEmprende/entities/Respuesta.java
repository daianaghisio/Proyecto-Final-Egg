package com.MigraEmprende.MigraEmprende.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import com.MigraEmprende.MigraEmprende.repositories.ComentarioRepository;

@Entity
public class Respuesta {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String contenido;
	private Date fecha;
	private Boolean alta;

	@OneToOne
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "comentario_id")
	private Comentario comentario;
	
	public Respuesta() {

	}

	public Respuesta(String id, String contenido, Date fecha, Boolean alta, Usuario usuario, Comentario comentario) {
		super();
		this.id = id;
		this.contenido = contenido;
		this.fecha = fecha;
		this.alta = alta;
		this.usuario = usuario;
		this.comentario = comentario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
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

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}		
	
	
	

	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", contenido=" + contenido + ", fecha=" + fecha + ", alta=" + alta + ", usuario="
				+ usuario + ", comentario=" + comentario + "]";
	}

}
