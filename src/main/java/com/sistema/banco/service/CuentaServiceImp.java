package com.sistema.banco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Cuenta;
import com.sistema.banco.repository.ClienteRepository;
import com.sistema.banco.repository.CuentaRepository;

@Service
public class CuentaServiceImp implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;
    private final TransaccionService transaccionService;

    public CuentaServiceImp(CuentaRepository cuentaRepository, ClienteRepository clienteRepository,
            TransaccionService transaccionService) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
        this.transaccionService = transaccionService;
    }

    @Override
    public List<Cuenta> listaCuenta() throws Exception {
        List<Cuenta> lista = cuentaRepository.findAll();
        return lista;
    }

    @Override
    public void guardarCuenta(Cuenta cuenta) throws Exception {
        cuenta.setSaldo(0.0);
        cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta buscarCuenta(String numeroCuenta) throws Exception {
        Cuenta usuario = cuentaRepository.findByNumeroCuenta(numeroCuenta);
        return usuario;
    }

    @Override
    public void transferenciaCliente(Long cuentaId, Double monto, String documento) throws Exception {

        Cliente clienteEmisor = clienteRepository.findById(cuentaId).get();
        Cliente clienteReceptor = clienteRepository.findByDocumento(documento);

        try {

        } catch (Exception e) {
            System.out.println("Error en la transferencia: " + e);
        }

    }

    @Override
    public void recargarCuenta(String numeroCuenta, Double monto, String emisor) throws Exception {
        try {

            Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);

            cuenta.setSaldo(cuenta.getSaldo() + monto);

            transaccionService.guardarTransacion(cuenta, monto, emisor);

        } catch (Exception e) {
            System.out.println("Error al recargar la cuenta: " + e);
        }
    }

    @Override
    public void retirarCuenta(Long cuentaId, Double monto) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retirarCuenta'");
    }

}
