package com.sistema.banco.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

                        .requestMatchers("/static/imgService/**", "/bank/**",
                                "/login",
                                "/correo/recuperar/**")
                        .permitAll()
                        .requestMatchers("/admin/**", "/correo/**")
                        .hasAuthority("Admin")
                        .requestMatchers(HttpMethod.POST, "/admin/guardarServicio").hasAuthority("Admin")
                        .requestMatchers("/cliente/**", "/correo/**").hasAuthority("User")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/bank/login")
                        .loginProcessingUrl("/bank/perform_login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/bank/login")
                        .permitAll());

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
