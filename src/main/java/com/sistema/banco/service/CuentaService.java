package com.sistema.banco.service;

import java.util.List;
import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Servicio;

public interface CuentaService {

    List<Cuenta> listaCuenta() throws Exception;

    public void guardarCuenta(Cuenta cuenta) throws Exception;

    Cuenta buscarCuenta(String numeroCuenta) throws Exception;

    public void retirarCuenta(String numeroCuenta, Double monto) throws Exception;

    public void recargarCuenta(String numeroCuenta, Double monto, String emisor) throws Exception;

    public void transferenciaCliente(String numeroCuenta, Double monto, Cuenta cuenta) throws Exception;

    public void procesoCompra(String numeroCuenta, Servicio servicio, String detalle) throws Exception;

}
