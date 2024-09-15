package com.sistema.banco.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.banco.dto.ClienteDto;
import com.sistema.banco.mappers.ClienteMappers;
import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Servicio;
import com.sistema.banco.models.Transaccion;
import com.sistema.banco.service.ClienteService;
import com.sistema.banco.service.ComprasService;
import com.sistema.banco.service.ServiciosService;
import com.sistema.banco.service.TransaccionService;

import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMappers clienteMappers;
    private final TransaccionService transaccionService;
    private final ServiciosService serviciosService;
    private final ComprasService comprasService;

    public ClienteController(ClienteService clienteService, ClienteMappers clienteMappers,
            TransaccionService transaccionService, ServiciosService serviciosService, ComprasService comprasService) {
        this.clienteService = clienteService;
        this.clienteMappers = clienteMappers;
        this.transaccionService = transaccionService;
        this.serviciosService = serviciosService;
        this.comprasService = comprasService;
    }

    @GetMapping("/usuario/{id}")
    public String incioCliente(@PathVariable String id, Model models) throws Exception {

        Cliente cliente = clienteService.buscarCliente(id);

        ClienteDto clienteDto = clienteMappers.toClienteDto(cliente);

        models.addAttribute("clienteDto", clienteDto);

        return "clienteVista/index";
    }

    @GetMapping("/movimientos/{id}/{numeroCuenta}")
    public String verMovimientos(@PathVariable(name = "id") String id, @PathVariable String numeroCuenta,
            @RequestParam(required = false) String tipo, Model model) throws Exception {

        // Obtener todos los movimientos del cliente por tipo
        Set<Transaccion> listaMovimientos = clienteService.mostrarMovimientos(numeroCuenta, tipo);

        // Obtener el cliente y mapearlo a ClienteDto
        Cliente cliente = clienteService.buscarCliente(id);
        ClienteDto clienteDto = clienteMappers.toClienteDto(cliente);

        // Añadir ClienteDto y lista de movimientos al modelo
        model.addAttribute("clienteDto", clienteDto);
        model.addAttribute("listaMovimientosFiltrados", listaMovimientos);

        return "clienteVista/movimientos"; // Nombre de la vista
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

    @GetMapping("/servicio/{id}")
    public String mostrarServicios(Model modelo, @PathVariable String id,
            @RequestParam(required = false) String categoria) throws Exception {

        Cliente cliente = clienteService.buscarCliente(id);

        ClienteDto clienteDto = clienteMappers.toClienteDto(cliente);

        modelo.addAttribute("clienteDto", clienteDto);

        List<Servicio> listaServicio;
        if (categoria != null && !categoria.isEmpty()) {
            listaServicio = serviciosService.buscarPorCategoria(categoria);
        } else {
            listaServicio = serviciosService.listarTodos();
        }

        modelo.addAttribute("listaServicio", listaServicio);

        return "clienteVista/servicio";
    }

    @GetMapping("/servicio2/{id}")
    @ResponseBody
    public Servicio obtenerServicio(@PathVariable Long id) {
        return serviciosService.buscarServicio(id);
    }

    @PostMapping("/guardarCompra")
    public String guardarCompras(@RequestParam("atributo") String atributo,
            @RequestParam("servicioId") Long servicioId,
            @RequestParam("clienteId") Long clienteId,
            RedirectAttributes redirectAttributes) {
        try {
            Servicio servicio = serviciosService.buscarServicio(servicioId);
            Cliente cliente = clienteService.buscarClienteId(clienteId);

            if (servicio == null || cliente == null) {
                redirectAttributes.addFlashAttribute("error", "Cliente o Servicio no encontrado");
                return "redirect:/cliente/error";
            }

            clienteService.realizarCompra(cliente, servicio, atributo);
            redirectAttributes.addFlashAttribute("mensaje", "Compra guardada con éxito!");

            return "redirect:/cliente/servicio/" + cliente.getDocumento();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al guardar la compra: " + e.getMessage());
            return "redirect:/cliente/error";
        }
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
