package com.MigraEmprende.MigraEmprende.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.MigraEmprende.MigraEmprende.entities.Emprendimiento;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, String> {
	
	@Query("SELECT a FROM Emprendimiento a WHERE a.usuario.id = :usuarioid")
	public List<Emprendimiento> retornarEmprendimientosPorUserId(@Param("usuarioid") String usuarioid);
	
}
