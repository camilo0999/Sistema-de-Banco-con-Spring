package com.sistema.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.sistema.banco.dto.CuentaDto;
import com.sistema.banco.mappers.CuentaMappers;
import com.sistema.banco.service.ClienteService;
import com.sistema.banco.service.CuentaService;
import com.sistema.banco.service.TransaccionService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ClienteService clienteService;
    private final CuentaService cuentaService;
    private final CuentaMappers cuentaMappers;

    public AdminController(ClienteService clienteService, CuentaService cuentaService, CuentaMappers cuentaMappers) {
        this.clienteService = clienteService;
        this.cuentaService = cuentaService;
        this.cuentaMappers = cuentaMappers;
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
        System.out.println(numeroCuenta);

        return "redirect:/admin/inicio";
    }

}
