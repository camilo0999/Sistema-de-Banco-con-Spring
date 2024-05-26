package com.sistema.banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.banco.models.Cuenta;
import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Cuenta findByNumeroCuenta(String numeroCuenta);

}
