package com.sistema.banco.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sistema.banco.models.Cliente;

public interface ClienteService {

    List<Cliente> listaCliente() throws Exception;

    public void guardarCliente(Cliente cliente) throws Exception;

    Cliente buscarCliente(String documento) throws Exception;

    String rutaImagen(MultipartFile file) throws Exception;

    public void editarCliente(Cliente viejoCliente, Cliente nuevCliente) throws Exception;

    public void eliminarCliente(String documento) throws Exception;

}
