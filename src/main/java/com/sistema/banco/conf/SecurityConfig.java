package com.sistema.banco.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sistema.banco.security.CustomAuthenticationSuccessHandler;
import com.sistema.banco.security.SecurityClienteDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final SecurityClienteDetailsService securityClienteDetailsService;

    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
            SecurityClienteDetailsService securityClienteDetailsService) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.securityClienteDetailsService = securityClienteDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**", "/img/**", "/js/**", "/css/**", "/bank/**", "/login").permitAll()
                        .requestMatchers("/cliente/usuario/**").permitAll() // Permitir acceso sin autenticación a
                                                                            // /cliente/usuario/**
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/bank/login")
                        .loginProcessingUrl("/bank/perform_login") // Configurar el endpoint de inicio de sesión
                        .usernameParameter("email") // Asegúrate de que coincida con el nombre del campo en el
                                                    // formulario
                        .passwordParameter("password") // Asegúrate de que coincida con el nombre del campo en el
                                                       // formulario
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .csrf().disable(); // Deshabilitar CSRF para simplificar la prueba

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(securityClienteDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // NOTA: No utilizar NoOpPasswordEncoder en producción, es solo para
                                                  // fines de demostración
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
