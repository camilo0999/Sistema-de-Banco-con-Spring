package com.sistema.banco.service;

import java.sql.SQLException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Transaccion;
import com.sistema.banco.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {

    private static Logger logger = LoggerFactory.getLogger(ClienteServiceImp.class);

    private final ClienteRepository clienteRepository;

    private final CuentaService cuentaService;

    private final TransaccionService transaccionService;

    public ClienteServiceImp(ClienteRepository clienteRepository, CuentaService cuentaService,
            TransaccionService transaccionService) {
        this.clienteRepository = clienteRepository;
        this.cuentaService = cuentaService;
        this.transaccionService = transaccionService;
    }

    @Override
    public List<Cliente> listaCliente() throws Exception {

        try {
            logger.info("Listado de cliente");
            return clienteRepository.findAll();

        } catch (Exception e) {
            logger.error("Error al cargar el listado de cliente: ", e);
            return Collections.emptyList();
        }

    }

    @Override
    public void guardarCliente(Cliente cliente) throws Exception {

        try {

            if (clienteRepository.existsByUsername(cliente.getUsername())) {
                throw new IllegalArgumentException("Este usuario ya esta registrado");
            }

            Cuenta cuenta = new Cuenta();

            cuenta.setNumeroCuenta(cliente.getDocumento().concat("-").concat("100"));

            cuentaService.guardarCuenta(cuenta);

            cuenta.setCliente(cliente);

            cliente.setCuenta(cuenta);

            cliente.setRoles("User");

            clienteRepository.save(cliente);

            logger.info("Se registro correctamente el Usuario: {}", cliente.getNombre());

        } catch (SQLException e) {
            logger.error("Error al registrar un cliente:", e);
        }

    }

    @Override
    public Cliente buscarCliente(String documento) throws Exception {

        return clienteRepository.findByDocumento(documento);
    }

    @Override
    public String rutaImagen(MultipartFile file) throws Exception {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String imagenProducto;

            if (fileName.contains("..")) {
                logger.warn("Imagen no seleccionada");
                throw new Exception("Nombre de archivo no válido: " + fileName);
            }

            imagenProducto = Base64.getEncoder().encodeToString(file.getBytes());
            return imagenProducto;

        } catch (Exception e) {
            logger.error("Error en la ruta imagen:", e);
        }
        return null;
    }

    @Override
    public void editarCliente(Cliente viejoCliente, Cliente nuevCliente) throws Exception {

        try {

            Cliente buscarCliente = clienteRepository.findById(viejoCliente.getId()).orElse(null);
            if (buscarCliente != null) {

                buscarCliente.setApellido(nuevCliente.getApellido());
                buscarCliente.setDireccion(nuevCliente.getDireccion());
                buscarCliente.setNombre(nuevCliente.getNombre());
                buscarCliente.setTelefono(nuevCliente.getTelefono());
                buscarCliente.setUsername(nuevCliente.getUsername());
            } else {
                logger.warn("No se encontro al Cliente: {}", viejoCliente.getNombre());
            }

        } catch (Exception e) {
            logger.error("Error al actualizar Cliente:", e);
        }

    }

    @Override
    public void eliminarCliente(String documento) throws Exception {
        try {
            Cliente cliente = clienteRepository.findByDocumento(documento);
            if (cliente != null) {
                clienteRepository.delete(cliente);
            } else {
                logger.info("No se encontro este documento en la base de datos: {}", documento);
            }

        } catch (Exception e) {
            logger.error("Error al eliminar ", e);
        }
    }

    @Override
    public Set<Transaccion> mostrarMovimientos(String numeroCuenta) throws Exception {

        try {

            Cuenta cuenta = cuentaService.buscarCuenta(numeroCuenta);

            logger.info("Listado de movimientos del Cliente: {}", cuenta.getCliente().getNombre());
            return transaccionService.movimientosCuenta(cuenta);

        } catch (Exception e) {
            logger.error("Error en ver movimientos: ", e);
        }
        return Collections.emptySet();

    }

}
