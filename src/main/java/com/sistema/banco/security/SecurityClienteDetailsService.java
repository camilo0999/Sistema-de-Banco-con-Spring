package com.sistema.banco.security;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityClienteDetailsService implements UserDetailsService {

    private final ClienteService clienteService;

    public SecurityClienteDetailsService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteService.buscarClienteUsername(username);
        if (cliente == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(cliente.getRoles()));

        return new User(cliente.getUsername(), cliente.getPassword(), authorities);
    }
}
