package com.sistema.banco.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.sistema.banco.models.Cuenta;
import com.sistema.banco.repository.CuentaRepository;

@Service
public class CuentaServiceImp implements CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaServiceImp(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
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

    }

    @Override
    public void retirarCuenta(Long cuentaId, Double monto) throws Exception {

    }

    @Override
    public void transferenciaCliente(Long cuentaId, Double monto, String documento) throws Exception {

    }

}
