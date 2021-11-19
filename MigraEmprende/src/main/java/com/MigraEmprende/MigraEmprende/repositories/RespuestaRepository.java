package com.MigraEmprende.MigraEmprende.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MigraEmprende.MigraEmprende.entities.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, String> {

	@Query("SELECT a FROM Respuesta a WHERE a.alta = TRUE and a.comentario.id = :comentario ORDER BY a.fecha ASC")
	public List<Respuesta> RetornarRespuestasEnTrue (@Param("comentario") String comentarioId);
	
}
