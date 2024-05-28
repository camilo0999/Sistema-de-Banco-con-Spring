package com.sistema.banco.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Transaccion;
import com.sistema.banco.repository.TransaccionRepository;

@Service
public class TransaccionServiceImp implements TransaccionService {

    private static Logger logger = LoggerFactory.getLogger(TransaccionServiceImp.class);

    private final TransaccionRepository transaccionRepository;

    public TransaccionServiceImp(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public List<Transaccion> listaTransaccion() throws Exception {

        try {

            return transaccionRepository.findAll();

        } catch (Exception e) {

            logger.error("Error al cargar la lista de movimientos", e);

            return Collections.emptyList();
        }

    }

    @Override
    public void guardarTransacion(Cuenta cuenta, Double monto, String emisor) throws Exception {

        try {

            LocalDateTime locaDate = LocalDateTime.now();

            Transaccion transaccion = new Transaccion();

            transaccion.setFecha(locaDate);

            transaccion.setMonto(monto);

            transaccion.setTipo("EMISOR: " + emisor);

            transaccion.setCuenta(cuenta);

            transaccionRepository.save(transaccion);

        } catch (Exception e) {

            logger.error("Error en guardar transacion: ", e);
        }

    }

    @Override
    public Set<Transaccion> movimientosCuenta(Cuenta cuenta) throws Exception {

        return transaccionRepository.findAllByCuenta(cuenta);
    }

    @Override
    public void guardarEnvio(Cuenta cuenta, Double saldo, String receptor) throws Exception {
        try {

            LocalDateTime locaDate = LocalDateTime.now();

            Transaccion transaccion = new Transaccion();

            transaccion.setFecha(locaDate);

            transaccion.setMonto(saldo);

            transaccion.setTipo("EMISOR: " + receptor);

            transaccion.setCuenta(cuenta);

            transaccionRepository.save(transaccion);

        } catch (Exception e) {

            logger.error("Error en guardar transacion: ", e);
        }
    }

}
