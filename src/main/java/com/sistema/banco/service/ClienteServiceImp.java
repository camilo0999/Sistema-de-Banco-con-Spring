package com.sistema.banco.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Cuenta;
import com.sistema.banco.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {

    private final ClienteRepository clienteRepository;

    private final CuentaService cuentaService;

    public ClienteServiceImp(ClienteRepository clienteRepository, CuentaService cuentaService) {
        this.clienteRepository = clienteRepository;
        this.cuentaService = cuentaService;
    }

    @Override
    public List<Cliente> listaCliente() throws Exception {
        List<Cliente> lista = clienteRepository.findAll();
        return lista;
    }

    @Override
    public void guardarCliente(Cliente cliente) throws Exception {
        if (clienteRepository.existsByUsername(cliente.getUsername())) {
            throw new IllegalArgumentException("Este usuario ya esta registrado");
        }

        try {

            Cuenta cuenta = new Cuenta();

            cuenta.setNumeroCuenta(cliente.getDocumento().concat("-").concat("100"));

            cuentaService.guardarCuenta(cuenta);

            cuenta.setCliente(cliente);

            cliente.setCuenta(cuenta);

            cliente.setRoles("User");

            clienteRepository.save(cliente);

        } catch (Exception e) {
            System.out.println("Error al crear cuenta de usuario: " + e);
        }

    }

    @Override
    public Optional<Cliente> buscarCliente(String documento) throws Exception {

        return clienteRepository.findByDocumento(documento);
    }

    @Override
    public String rutaImagen(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String imagenProducto;
        if (fileName.contains("..")) {
            System.out.println("Imagen no seleccionada");
        }
        try {
            imagenProducto = (Base64.getEncoder().encodeToString(file.getBytes()));
            return imagenProducto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void editarCliente(Cliente viejoCliente, Cliente nuevCliente) throws Exception {

        try {

            Cliente buscarCliente = clienteRepository.findById(viejoCliente.getId()).get();

            buscarCliente.setApellido(nuevCliente.getApellido());
            buscarCliente.setDireccion(nuevCliente.getDireccion());
            buscarCliente.setNombre(nuevCliente.getNombre());
            buscarCliente.setTelefono(nuevCliente.getTelefono());
            buscarCliente.setUsername(nuevCliente.getUsername());

        } catch (Exception e) {
            System.out.println("Error al actualizar el Cliente: " + e);
        }

    }

    @Override
    public void eliminarCliente(String documento) throws Exception {
        try {

            Cliente cliente = clienteRepository.findByDocumento(documento).get();
            clienteRepository.delete(cliente);

        } catch (Exception e) {
            System.out.println("Error al eliminar al cliente: " + e);
        }
    }

}
