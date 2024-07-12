package com.sistema.banco.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.service.ClienteService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ClienteService clienteService;

    public CustomAuthenticationSuccessHandler(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        try {
            Cliente cliente = clienteService.buscarClienteUsername(username);
            String rol = cliente.getRoles();
            if (cliente != null) {

                if (rol.equals("User")) {
                    String redirectUrl = "/cliente/usuario/" + cliente.getDocumento();
                    response.sendRedirect(redirectUrl);
                } else if (rol.equals("Admin")) {
                    String redirectUrl = "/admin/inicio";
                    response.sendRedirect(redirectUrl);
                }

            } else {
                response.sendRedirect("/bank/login?error=ClienteNoEncontrado");
            }
        } catch (Exception e) {
            response.sendRedirect("/bank/login?error=ErrorInterno");
        }
    }
}
