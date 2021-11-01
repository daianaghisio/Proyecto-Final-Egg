package com.MigraEmprende.MigraEmprende.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MigraEmprende.MigraEmprende.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
