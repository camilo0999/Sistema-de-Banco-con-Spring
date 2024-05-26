package com.sistema.banco.service;

import java.util.List;

import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Transaccion;

public interface TransaccionService {

    List<Transaccion> listaTransaccion() throws Exception;

    public void guardarTransacion(Cuenta cuenta, Double saldo, String emisor) throws Exception;

}
