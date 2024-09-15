package com.sistema.banco.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Transaccion;

// Creacion de la tabla Transaccion en la base de datos con todos los metodos de un CRUD
@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    // Este metodo devuelve una lista tipo set del objecto transaccion, le pasamos
    // un objecto tipo cuenta como parametro y el busca todas las transaciones que a
    // tenido esa cuenta.
    Set<Transaccion> findAllByCuenta(Cuenta cuenta);

    List<Transaccion> findAllByTipo(String tipo);

}
