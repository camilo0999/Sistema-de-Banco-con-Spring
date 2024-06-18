package com.sistema.banco.controller;

import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.banco.dto.ClienteDto;
import com.sistema.banco.mappers.ClienteMappers;
import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Cuenta;
import com.sistema.banco.models.Transaccion;
import com.sistema.banco.service.ClienteService;
import com.sistema.banco.service.CuentaService;
import com.sistema.banco.service.TransaccionService;

import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMappers clienteMappers;
    private final TransaccionService transaccionService;
    private final CuentaService cuentaService;

    public ClienteController(ClienteService clienteService, ClienteMappers clienteMappers,
            TransaccionService transaccionService, CuentaService cuentaService) {
        this.clienteService = clienteService;
        this.clienteMappers = clienteMappers;
        this.transaccionService = transaccionService;
        this.cuentaService = cuentaService;
    }

    @GetMapping("/usuario/{id}")
    public String incioCliente(@PathVariable String id, Model models) throws Exception {

        Cliente cliente = clienteService.buscarCliente(id);

        ClienteDto clienteDto = clienteMappers.toClienteDto(cliente);

        models.addAttribute("clienteDto", clienteDto);

        return "clienteVista/index";
    }

    @GetMapping("/movimientos/{id}/{numeroCuenta}")
    public String clienteMovimientos(@PathVariable(name = "id") String id,
            @PathVariable(name = "numeroCuenta") String numeroCuenta, Model models) throws Exception {

        Set<Transaccion> listaMovimientos = clienteService.mostrarMovimientos(numeroCuenta);

        Cliente cliente = clienteService.buscarCliente(id);

        ClienteDto clienteDto = clienteMappers.toClienteDto(cliente);

        models.addAttribute("clienteDto", clienteDto);

        models.addAttribute("listaMovimientos", listaMovimientos);

        return "clienteVista/movimientos";
    }

    @GetMapping("/transferencia/{id}/{numeroCuenta}")
    public String transferenciaCLiente(@PathVariable(name = "id") String id,
            @PathVariable(name = "numeroCuenta") String numeroCuenta, Model models) throws Exception {

        Cliente cliente = clienteService.buscarCliente(id);

        ClienteDto clienteDto = clienteMappers.toClienteDto(cliente);

        models.addAttribute("clienteDto", clienteDto);
        return "clienteVista/enviar";
    }

    @PostMapping("/transferencia/{id}/enviar")
    public String clienteTransaccion(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "numeroCuenta") String numeroCuenta,
            @RequestParam(name = "monto") Double monto) throws Exception {

        Cliente cliente = clienteService.buscarCliente(id);

        clienteService.enviarTransferecnia(numeroCuenta, monto, cliente.getCuenta());

        return "redirect:/cliente/movimientos/" + id + "/" + numeroCuenta;
    }

    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("clienteDto") ClienteDto clienteDto) throws Exception {

        Cliente cliente = clienteMappers.toCliente(clienteDto);

        clienteService.guardarCliente(cliente);

        return "redirect:/cliente/login";
    }

    @GetMapping("/reporte/{idTransacion}")
    public ResponseEntity<byte[]> exportarFactura(@PathVariable(name = "idTransacion") Long idTransacion)
            throws JRException {

        try {

            byte[] pdfReport = transaccionService.exportPdf(idTransacion);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfReport);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

    }

}
