package com.sistema.banco.service;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Servicio;

public interface ComprasService {

    public void guardarCompras(Cliente cliente, Servicio servicio, String detalle);

}
