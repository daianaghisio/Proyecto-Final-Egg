package com.MigraEmprende.MigraEmprende.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MigraEmprende.MigraEmprende.entities.Emprendimiento;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, String> {
	
	
}
