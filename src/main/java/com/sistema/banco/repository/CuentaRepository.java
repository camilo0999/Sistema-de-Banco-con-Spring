package com.sistema.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.banco.models.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

}
