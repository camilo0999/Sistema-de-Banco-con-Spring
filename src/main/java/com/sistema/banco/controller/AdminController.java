package com.sistema.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.banco.dto.CuentaDto;
import com.sistema.banco.dto.ServiciosDto;
import com.sistema.banco.mappers.ServiciosMappers;
import com.sistema.banco.models.Servicio;
import com.sistema.banco.service.ClienteService;
import com.sistema.banco.service.CuentaService;
import com.sistema.banco.service.ServiciosService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ClienteService clienteService;
    private final CuentaService cuentaService;
    private final ServiciosService serviciosService;
    private final ServiciosMappers serviciosMappers;

    public AdminController(ClienteService clienteService, CuentaService cuentaService,
            ServiciosService serviciosService, ServiciosMappers serviciosMappers) {
        this.clienteService = clienteService;
        this.cuentaService = cuentaService;
        this.serviciosService = serviciosService;
        this.serviciosMappers = serviciosMappers;
    }

    @GetMapping("/inicio")
    public String mostrarInicio(Model models) throws Exception {

        models.addAttribute("listaCliente", clienteService.listaCliente());
        models.addAttribute("cuenta", new CuentaDto());
        return "adminVista/index";
    }

    @GetMapping("/servicio")
    public String mostrarServicios(Model modelo) {
        modelo.addAttribute("listaServicio", serviciosService.lista());
        modelo.addAttribute("ServiciosDto", new Servicio());
        return "adminVista/servicio";
    }

    @GetMapping("eliminarServicio")
    public String eliminarServicio(@RequestParam("id") Long id) {

        serviciosService.eliminarServicio(id);

        return "redirect:/admin/servicio";
    }

    @PostMapping("/guardarServicio")
    public String guardarServicio(@ModelAttribute ServiciosDto serviciosDto, @RequestParam("file") MultipartFile file) {
        try {
            Servicio servicio = serviciosMappers.toServicio(serviciosDto);
            serviciosService.guardarServicio(servicio, file);
            return "redirect:/admin/servicio";
        } catch (Exception e) {
            // Manejo básico de errores
            e.printStackTrace();
            return "error"; // Asegúrate de tener una vista de error
        }
    }

    @PostMapping("/recargar")
    public String guardarProducto(@RequestParam("numeroCuenta") String numeroCuenta,
            @RequestParam("monto") Double monto) throws Exception {

        String emisor = "CORRESPONSAL-BANCARIO-BANK";

        cuentaService.recargarCuenta(numeroCuenta, monto, emisor);

        return "redirect:/admin/inicio";
    }

}
