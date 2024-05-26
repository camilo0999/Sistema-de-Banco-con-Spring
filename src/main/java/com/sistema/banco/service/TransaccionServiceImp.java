package com.sistema.banco.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Transaccion;
import com.sistema.banco.repository.TransaccionRepository;

@Service
public class TransaccionServiceImp implements TransaccionService {

    private final TransaccionRepository transaccionRepository;

    public TransaccionServiceImp(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    public List<Transaccion> listaTransaccion() throws Exception {
        List<Transaccion> lista = transaccionRepository.findAll();
        return lista;
    }

    @Override
    public void guardarTransacion(Cuenta cuenta, Double monto, String emisor) throws Exception {

        try {

            LocalDateTime locaDate = LocalDateTime.now();

            Transaccion transaccion = new Transaccion();

            transaccion.setFecha(locaDate);

            transaccion.setMonto(monto);

            transaccion.setTipo(emisor);

            transaccion.setCuenta(cuenta);

            transaccionRepository.save(transaccion);

        } catch (Exception e) {
            System.out.println("Error en la transacion: " + e);
        }

    }

}
