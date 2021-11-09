package com.MigraEmprende.MigraEmprende.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String nombreYApellido;
	private String username;
	private String password;
	private String email;
	private Boolean alta;
	private String rol;
	
	@OneToOne
    private Foto foto;
	
	
	public Usuario() {

	}

	public Usuario(String id, String nombreYApellido, String username, String password, String email, Boolean alta, String rol) {
		super();
		this.id = id;
		this.nombreYApellido = nombreYApellido;
		this.username = username;
		this.password = password;
		this.email = email;
		this.alta = alta;
		this.rol = rol;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreYApellido() {
		return nombreYApellido;
	}

	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAlta() {
		return alta;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}
	
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreYApellido=" + nombreYApellido + ", username=" + username + ", password="
				+ password + ", email=" + email + ", alta=" + alta + ", foto=" + foto + "]";
	}

	

}
