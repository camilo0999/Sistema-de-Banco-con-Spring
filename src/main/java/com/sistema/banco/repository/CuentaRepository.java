package com.sistema.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.banco.models.Cuenta;

// Creacion de la tabla Cuenta en la base de datos con todos los metodos de un CRUD
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    // Devuelve un objecto de cuenta, el cual busca un numero de cuenta en la tabla
    // cuenta.
    Cuenta findByNumeroCuenta(String numeroCuenta);

}
