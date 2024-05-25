package com.sistema.banco.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documento;
    private String nombre;
    private String apellido;
    private String password;
    private String telefono;
    private String username;
    private String direccion;
    private String imagen;
    private String roles;

    @OneToOne(mappedBy = "cliente")
    private Cuenta cuenta;

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

}
