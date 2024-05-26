package com.sistema.banco.controller;

import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.banco.dto.ClienteDto;
import com.sistema.banco.dto.TransaccionDto;
import com.sistema.banco.mappers.ClienteMappers;
import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Transaccion;
import com.sistema.banco.service.ClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMappers clienteMappers;

    public ClienteController(ClienteService clienteService, ClienteMappers clienteMappers) {
        this.clienteService = clienteService;
        this.clienteMappers = clienteMappers;
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

    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("clienteDto") ClienteDto clienteDto) throws Exception {

        Cliente cliente = clienteMappers.toCliente(clienteDto);

        clienteService.guardarCliente(cliente);

        return "redirect:/cliente/login";
    }

}
