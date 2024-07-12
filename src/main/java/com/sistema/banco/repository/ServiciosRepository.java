package com.sistema.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.banco.models.Servicio;

@Repository
public interface ServiciosRepository extends JpaRepository<Servicio, Long> {

}
