package com.sistema.banco.security;

/*
 * 
 * import org.springframework.security.core.GrantedAuthority;
 * import org.springframework.security.core.authority.SimpleGrantedAuthority;
 * import org.springframework.security.core.userdetails.UserDetails;
 * 
 * import com.sistema.banco.models.Cliente;
 * 
 * 
 * 
 * import java.util.Arrays;
 * import java.util.Collection;
 * import java.util.stream.Collectors;
 * 
 * public class UserDetailModel implements UserDetails {
 * 
 * private final Cliente user;
 * 
 * public UserDetailModel(Cliente user) {
 * this.user = user;
 * }
 * 
 * @Override
 * public Collection<? extends GrantedAuthority> getAuthorities() {
 * return Arrays.stream(user.getRoles().split(","))
 * .map(SimpleGrantedAuthority::new)
 * .collect(Collectors.toList());
 * }
 * 
 * @Override
 * public String getPassword() {
 * return user.getPassword();
 * }
 * 
 * @Override
 * public String getUsername() {
 * return user.getUsername();
 * }
 * 
 * @Override
 * public boolean isAccountNonExpired() {
 * return true;
 * }
 * 
 * @Override
 * public boolean isAccountNonLocked() {
 * return true;
 * }
 * 
 * @Override
 * public boolean isCredentialsNonExpired() {
 * return true;
 * }
 * 
 * @Override
 * public boolean isEnabled() {
 * return true;
 * }
 * }
 */