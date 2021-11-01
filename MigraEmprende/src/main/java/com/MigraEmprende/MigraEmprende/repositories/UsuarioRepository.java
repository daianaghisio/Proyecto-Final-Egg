package com.MigraEmprende.MigraEmprende.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MigraEmprende.MigraEmprende.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	@Query("SELECT a FROM Usuario a WHERE a.username = :username")
	public Usuario retornarUsuarioPorUsername(@Param("username") String username);
	
	@Query("SELECT a FROM Usuario a WHERE a.email = :email")
	public Usuario retornarUsuarioPorEmail(@Param("email") String email);
}
