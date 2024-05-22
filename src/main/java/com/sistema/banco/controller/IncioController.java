package com.sistema.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.banco.dto.ClienteDto;
import com.sistema.banco.mappers.ClienteMappers;
import com.sistema.banco.models.Cliente;
import com.sistema.banco.service.ClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/bank")
public class IncioController {

    private final ClienteService clienteService;
    private final ClienteMappers clienteMappers;

    public IncioController(ClienteService clienteService, ClienteMappers clienteMappers) {
        this.clienteService = clienteService;
        this.clienteMappers = clienteMappers;
    }

    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "incioVista/index";
    }

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model models) {
        models.addAttribute("clienteDto", new ClienteDto());

        return "incioVista/registro";
    }

    @GetMapping("/login")
    public String mostrarFormularioIniciarSesion(Model models) {

        return "incioVista/login";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("clienteDto") ClienteDto clienteDto) throws Exception {

        Cliente cliente = clienteMappers.toCliente(clienteDto);

        clienteService.guardarCliente(cliente);

        return "redirect:/bank/login";
    }

    @PostMapping("/perform_login")
    public String iniciarSesion() {

        return "redirect:/cliente/incio";
    }

}
