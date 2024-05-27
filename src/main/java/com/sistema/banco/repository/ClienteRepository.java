package com.sistema.banco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.banco.models.Cliente;

// Creacion de la tabla cliente en la base de datos con todos los metodos de un CRUD
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Devuelve un opcional de clientes, el cual busca si username existe en la base
    // de datos
    Optional<Cliente> findByUsername(String username);

    // Devuelve un objecto de cliente, este metodo busca por el documento en la
    // tabla cliente
    Cliente findByDocumento(String documento);

    // Este metodo devuelve un boolean si el usuario existe o no en la tabla cliente
    boolean existsByUsername(String username);

}
