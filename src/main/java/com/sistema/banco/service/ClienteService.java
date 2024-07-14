package com.sistema.banco.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Servicio;
import com.sistema.banco.models.Transaccion;

@Service
public interface ClienteService {

    List<Cliente> listaCliente() throws Exception;

    public void guardarCliente(Cliente cliente) throws Exception;

    Cliente buscarCliente(String documento) throws Exception;

    String rutaImagen(MultipartFile file) throws Exception;

    public void editarCliente(Cliente viejoCliente, Cliente nuevCliente) throws Exception;

    public void eliminarCliente(String documento) throws Exception;

    Set<Transaccion> mostrarMovimientos(String numeroCuenta, String tipo) throws Exception;

    public void enviarTransferecnia(String numeroCuenta, Double monto, Cuenta cuenta) throws Exception;

    public Cliente buscarClienteUsername(String username);

    public void recuperarCuenta(String username, String contrasena);

    public Cliente buscarClienteId(Long id);

    public void realizarCompra(Cliente cliente, Servicio servicio, String atributo) throws Exception;

}
