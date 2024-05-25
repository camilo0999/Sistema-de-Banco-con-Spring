package com.sistema.banco.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Cuenta;
import com.sistema.banco.repository.ClienteRepository;
import com.sistema.banco.repository.CuentaRepository;

@Service
public class CuentaServiceImp implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaServiceImp(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cuenta> listaCuenta() throws Exception {
        List<Cuenta> lista = cuentaRepository.findAll();
        return lista;
    }

    @Override
    public void guardarCuenta(Cuenta cuenta) throws Exception {
        cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta buscarCuenta(Long id) throws Exception {
        Cuenta usuario = cuentaRepository.findById(id).get();
        return usuario;
    }

    @Override
    public void depositarCuenta(Long cuentaId, Double monto) throws Exception {

        try {

            Cuenta cuenta = cuentaRepository.findById(cuentaId).get();
            cuenta.setSaldo(monto);

        } catch (Exception e) {
            System.out.println("Error al depositar a la cuenta N° " + cuentaId + ": " + e);
        }

    }

    @Override
    public void retirarCuenta(Long cuentaId, Double monto) throws Exception {

        try {
            Cuenta cuenta = cuentaRepository.findById(cuentaId).get();
            if (monto > cuenta.getSaldo()) {

                System.out.println("Saldo insuficiente, no puedes retirar esta cantidad: " + monto);
                System.out.println("Yu saldo disponible: " + cuenta.getSaldo());
            } else {
                cuenta.setSaldo(cuenta.getSaldo() - monto);
                System.out.println("Retiro exitoso");
            }

        } catch (Exception e) {
            System.out.println("Error al retirar en la cuenta N° " + cuentaId + ":" + e);
        }

    }

    @Override
    public void transferenciaCliente(Long cuentaId, Double monto, String documento) throws Exception {

        Cliente clienteEmisor = clienteRepository.findById(cuentaId).get();
        Cliente clienteReceptor = clienteRepository.findByDocumento(documento).get();

        try {

        } catch (Exception e) {
            System.out.println("Error en la transferencia: " + e);
        }

    }

}
