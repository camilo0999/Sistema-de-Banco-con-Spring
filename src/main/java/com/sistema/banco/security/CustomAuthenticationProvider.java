package com.sistema.banco.security;

/*
 * 
 * import org.springframework.security.authentication.AuthenticationProvider;
 * import org.springframework.security.authentication.BadCredentialsException;
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken;
 * import org.springframework.security.core.userdetails.UserDetails;
 * import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Component;
 * 
 * @Component
 * public class CustomAuthenticationProvider implements AuthenticationProvider {
 * 
 * private UserDetailsService userDetailsService;
 * 
 * @Override
 * public org.springframework.security.core.Authentication authenticate(
 * org.springframework.security.core.Authentication authentication)
 * throws org.springframework.security.core.AuthenticationException {
 * try {
 * UserDetails userDetails =
 * userDetailsService.loadUserByUsername(authentication.getName());
 * return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
 * userDetails.getPassword(),
 * userDetails.getAuthorities());
 * } catch (UsernameNotFoundException e) {
 * throw new BadCredentialsException("Invalid Credentials");
 * }
 * }
 * 
 * @Override
 * public boolean supports(Class<?> authentication) {
 * return
 * UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
 * }
 * 
 * }
 * 
 */