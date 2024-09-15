package com.sistema.banco.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//Creacion de la entidad de la base de datos cliente con sus respectivos atributos,
//gerando sus Getter y Setter para la manipulacion de los datos.
@Entity
@Table(name = "cliente")
public class Cliente {

    // la estregia es un id unico por tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Este valor es unico en la tabla cliente
    @Column(name = "documento", unique = true)
    private String documento;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "contraseña")
    private String password;

    @Column(name = "telefono")
    private String telefono;

    // Este valor es unico en la tabla cliente
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "foto")
    private String imagen;

    private String roles;

    // Relacion de uno a uno con la tabla Cuenta, ya que un cliente solo tendra una
    // cuenta en este sistema
    @OneToOne(mappedBy = "cliente")
    private Cuenta cuenta;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    // Los constructores que usa la clase
    public Cliente(Long id, String documento, String nombre, String apellido, String password, String telefono,
            String username, String direccion, String imagen, String roles) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.telefono = telefono;
        this.username = username;
        this.direccion = direccion;
        this.imagen = imagen;
        this.roles = roles;
    }

    public Cliente(String documento, String nombre, String apellido, String password, String telefono, String username,
            String direccion, String imagen, String roles) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.telefono = telefono;
        this.username = username;
        this.direccion = direccion;
        this.imagen = imagen;
        this.roles = roles;
    }

    public Cliente(String documento, String nombre, String apellido, String password, String telefono,
            String username) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.telefono = telefono;
        this.username = username;
    }

    public Cliente(String documento, String nombre, String apellido, String password, String telefono,
            String username, Cuenta cuenta) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.telefono = telefono;
        this.username = username;
        this.cuenta = cuenta;
    }

    public Cliente(String documento, String nombre, String apellido, String password, String telefono, String username,
            String direccion, String roles) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.telefono = telefono;
        this.username = username;
        this.direccion = direccion;
        this.roles = roles;
    }

    public Cliente() {
    }

    public Cliente(Long id) {
        this.id = id;
    }

    // Getter y Setter para manipular los datos

    public Cuenta getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRoles() {
        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean getAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public boolean getCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
