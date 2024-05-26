package com.sistema.banco.service;

import java.util.List;
import java.util.Set;

import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Transaccion;

public interface TransaccionService {

    List<Transaccion> listaTransaccion() throws Exception;

    Set<Transaccion> movimientosCuenta(Cuenta cuenta) throws Exception;

    public void guardarTransacion(Cuenta cuenta, Double saldo, String emisor) throws Exception;

}
