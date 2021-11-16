package com.MigraEmprende.MigraEmprende.entities;

//import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Emprendimiento {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String nombre;
	private String descripcion;
	private String email;
	private Boolean alta;
	//agrego:
	private String address;
	private String phone;
	private String facebook;
	private String instagram;
	
	

	@OneToOne
	private Usuario usuario;
	
	@OneToOne
	private Foto foto;
	/*
	@OneToMany
	private List<Foto> listaDeFotos;
	 */
	
	
	public Emprendimiento() {

	}
	public Emprendimiento(String id, String nombre, String descripcion, String email, Boolean alta, String address,
			String phone, String facebook, String instagram, Usuario usuario, Foto foto) {
		
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.email = email;
		this.alta = alta;
		this.address = address;
		this.phone = phone;
		this.facebook = facebook;
		this.instagram = instagram;
		this.usuario = usuario;
		this.foto = foto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getInstagram() {
		return instagram;
	}
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Foto getFoto() {
		return foto;
	}
	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	
	

}
