package com.sistema.banco.security;

import com.sistema.banco.models.Cliente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private Cliente cliente;

    public SecurityUser(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return cliente.getPassword();
    }

    @Override
    public String getUsername() {
        return cliente.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return cliente.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return cliente.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return cliente.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return cliente.isEnabled();
    }
}
