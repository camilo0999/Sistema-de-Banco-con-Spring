package com.sistema.banco.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImp(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listaCliente() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listaCliente'");
    }

    @Override
    public void guardarCliente(Cliente cliente) throws Exception {
        if (clienteRepository.existsByUsername(cliente.getUsername())) {
            throw new IllegalArgumentException("Este usuario ya esta registrado");
        }

        cliente.setRoles("User");
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarCliente(String documento) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarCliente'");
    }

    @Override
    public String rutaImagen(MultipartFile file) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rutaImagen'");
    }

    @Override
    public void editarCliente(Cliente viejoCliente, Cliente nuevCliente) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarCliente'");
    }

    @Override
    public void eliminarCliente(String documento) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarCliente'");
    }

}
