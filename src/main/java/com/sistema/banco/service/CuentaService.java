package com.sistema.banco.service;

import java.util.List;
import com.sistema.banco.models.Cuenta;

public interface CuentaService {

    List<Cuenta> listaCuenta() throws Exception;

    public void guardarCuenta(Cuenta cuenta) throws Exception;

    Cuenta buscarCuenta(Long id) throws Exception;

    public void depositarCuenta(Long cuentaId, Double monto) throws Exception;

    public void retirarCuenta(Long cuentaId, Double monto) throws Exception;

    public void transferenciaCliente(Long cuentaId, Double monto, String documento) throws Exception;

}
