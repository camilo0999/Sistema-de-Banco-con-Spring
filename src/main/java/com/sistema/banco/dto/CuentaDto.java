package com.sistema.banco.dto;

import java.util.Set;

import com.sistema.banco.models.Cliente;
import com.sistema.banco.models.Transaccion;

//De acuerdo al patro de diseño Dto estoy mappiando la entidad Cuenta del la base de datos para enviar los datos al cliente.
public class CuentaDto {

    private Long id;

    private String numeroCuenta;

    private Double saldo;

    private Cliente cliente;

    private Set<Transaccion> transacciones;

    public CuentaDto(Long id, String numeroCuenta, Double saldo, Cliente cliente, Set<Transaccion> transacciones) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.transacciones = transacciones;
    }

    public CuentaDto(String numeroCuenta, Double saldo, Cliente cliente, Set<Transaccion> transacciones) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.transacciones = transacciones;
    }

    public CuentaDto() {
    }

    public CuentaDto(Cliente cliente, String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.cliente = cliente;
    }

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
