package com.MigraEmprende.MigraEmprende.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MigraEmprende.MigraEmprende.entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, String>{

}
