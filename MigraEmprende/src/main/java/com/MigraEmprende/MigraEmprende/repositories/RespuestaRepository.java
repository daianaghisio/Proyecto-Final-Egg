package com.MigraEmprende.MigraEmprende.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MigraEmprende.MigraEmprende.entities.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, String> {

}
