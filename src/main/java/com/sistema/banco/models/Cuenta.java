package com.sistema.banco.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Creacion de la entidad de la base de datos cuenta con sus respectivos atributos,
//gerando sus Getter y Setter para la manipulacion de los datos.
@Entity
@Table(name = "cuenta")
public class Cuenta {

    // la estregia es un id unico por tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Numeros de cuentas unicos
    @Column(name = "numeroCuenta", unique = true)
    private String numeroCuenta;

    @Column(name = "saldo")
    private Double saldo;

    // Estoy definiendo que la carga sea peresoza, osea que solo carguen estos datos
    // cuando los llame.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaccion> transacciones;

    // Metodoso constructores
    public Cuenta(Long id, String numeroCuenta, Double saldo, Cliente cliente, Set<Transaccion> transacciones) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.transacciones = transacciones;
    }

    public Cuenta(String numeroCuenta, Double saldo, Cliente cliente, Set<Transaccion> transacciones) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.transacciones = transacciones;
    }

    public Cuenta(Cliente cliente, String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.cliente = cliente;
    }

    public Cuenta() {
    }

    // Metodos getter y setter para la manipulacion de los datos.

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Transaccion> getTransacciones() {
        return this.transacciones;
    }

    public void setTransacciones(Set<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

}
