package com.sistema.banco.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Servicio;
import com.sistema.banco.repository.CuentaRepository;

@Service
public class CuentaServiceImp implements CuentaService {

    private static Logger logger = LoggerFactory.getLogger(CuentaServiceImp.class);

    private final CuentaRepository cuentaRepository;
    private final TransaccionService transaccionService;

    public CuentaServiceImp(CuentaRepository cuentaRepository, @Lazy TransaccionService transaccionService) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionService = transaccionService;
    }

    @Override
    public List<Cuenta> listaCuenta() throws Exception {
        try {

            logger.info("Listado de cuenta");
            return cuentaRepository.findAll();

        } catch (Exception e) {
            logger.error("Error al listar las cuentas", e);
            return Collections.emptyList();
        }

    }

    @Override
    public void guardarCuenta(Cuenta cuenta) throws Exception {

        try {
            if (cuenta != null) {
                logger.info("Se registro corretamente la Cuenta: {}", cuenta.getNumeroCuenta());
                cuenta.setSaldo(0.0);
                cuentaRepository.save(cuenta);
            } else {
                logger.warn("No se puedo registrar la Cuenta, es null");
            }
        } catch (Exception e) {
            logger.error("Error al crear Cuuenta: ", e);
        }

    }

    @Override
    public Cuenta buscarCuenta(String numeroCuenta) throws Exception {

        try {

            return cuentaRepository.findByNumeroCuenta(numeroCuenta);

        } catch (Exception e) {
            logger.error("Error al buscar la cuenta:", e);
        }

        return null;
    }

    @Override
    public void transferenciaCliente(String numeroCuenta, Double monto, Cuenta cuenta) throws Exception {

        try {

            Cuenta receptor = buscarCuenta(numeroCuenta);

            receptor.setSaldo(receptor.getSaldo() + monto);

            cuenta.setSaldo(cuenta.getSaldo() - monto);

            String nombre1 = cuenta.getCliente().getNombre();

            String nombre2 = receptor.getCliente().getNombre();

            transaccionService.guardarTransacion(cuenta, monto, nombre1);

            transaccionService.guardarEnvio(receptor, monto, nombre2);

            logger.info("Transaccion exitosa");

        } catch (Exception e) {
            logger.error("Error al realizar la transacion: ", e);
        }

    }

    @Override
    public void recargarCuenta(String numeroCuenta, Double monto, String emisor) throws Exception {
        try {

            Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);

            cuenta.setSaldo(cuenta.getSaldo() + monto);

            transaccionService.guardarRecarga(cuenta, monto, emisor);

            logger.info("Se realizo exitosamente la recarga de saldo del Admin: {}", emisor);

        } catch (Exception e) {
            logger.error("Error al realizar la recarga de saldo: ", e);
        }
    }

    @Override
    public void retirarCuenta(String numeroCuenta, Double monto) throws Exception {

        try {

            Cuenta cuenta = buscarCuenta(numeroCuenta);

            cuenta.setSaldo(cuenta.getSaldo() - monto);

            transaccionService.guardarTransacion(cuenta, monto, cuenta.getCliente().getNombre());

            logger.info("Se realizo correctamente el retiro de la Cuenta N°: {}", numeroCuenta);

        } catch (Exception e) {
            logger.error("Error al realizar retiro: ", e);
        }

    }

    @Override
    public void procesoCompra(String numeroCuenta, Servicio servicio, String detalle) throws Exception {

        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);

        cuenta.setSaldo(cuenta.getSaldo() - servicio.getPrecio());

        transaccionService.guardarCompra(cuenta, servicio.getPrecio(), servicio, detalle);

    }

}
