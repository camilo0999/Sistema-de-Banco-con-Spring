package com.sistema.banco.security;

import com.sistema.banco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityClienteDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = this.userRepository.findByUsername(username);

        if(user != null){
            return new SecurityUser(user);
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}
