package com.sistema.banco.service;

import java.util.List;
import java.util.Set;

import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Transaccion;

import net.sf.jasperreports.engine.JRException;

public interface TransaccionService {

    List<Transaccion> listaTransaccion() throws Exception;

    Set<Transaccion> movimientosCuenta(Cuenta cuenta) throws Exception;

    public void guardarTransacion(Cuenta cuenta, Double saldo, String emisor) throws Exception;

    public void guardarEnvio(Cuenta cuenta, Double saldo, String receptor) throws Exception;

    public byte[] exportPdf(Long id) throws JRException;

}
