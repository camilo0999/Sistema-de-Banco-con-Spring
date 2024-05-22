package com.sistema.banco.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.repository.ClienteRepository;
import com.sistema.banco.security.UserDetailModel;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClienteRepository userRepository;

    public UserDetailsServiceImpl(ClienteRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> user = userRepository.findByUsername(username);
        return user.map(UserDetailModel::new)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Username"));
    }
}
