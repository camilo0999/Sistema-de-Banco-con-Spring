package com.sistema.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sistema.banco.dto.CuentaDto;
import com.sistema.banco.service.ClienteService;
import com.sistema.banco.service.CuentaService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ClienteService clienteService;
    private final CuentaService cuentaService;

    public AdminController(ClienteService clienteService, CuentaService cuentaService) {
        this.clienteService = clienteService;
        this.cuentaService = cuentaService;
    }

    @GetMapping("/inicio")
    public String mostrarInicio(Model models) throws Exception {

        models.addAttribute("listaCliente", clienteService.listaCliente());
        models.addAttribute("cuenta", new CuentaDto());
        return "adminVista/index";
    }

    @PostMapping("/recargar")
    public String guardarProducto(@RequestParam("numeroCuenta") String numeroCuenta,
            @RequestParam("monto") Double monto) throws Exception {

        String emisor = "CORRESPONSAL-BANCARIO-BANK";

        cuentaService.recargarCuenta(numeroCuenta, monto, emisor);

        return "redirect:/admin/inicio";
    }

}
