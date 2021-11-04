package com.MigraEmprende.MigraEmprende.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MigraEmprende.MigraEmprende.entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, String>{

	@Query("SELECT a FROM Comentario a WHERE a.username = :username")
	public List<Comentario> retornarComentarioPorUsername(@Param("username") String username);
	
	@Query("SELECT a FROM Comentario a WHERE a.email = :email")
	public List<Comentario> retornarComentarioPorEmail(@Param("email") String email);
	
}
