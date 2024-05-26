package com.sistema.banco.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Transaccion;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    Set<Transaccion> findAllByCuenta(Cuenta cuenta);

}
