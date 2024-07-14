package com.sistema.banco.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Creacion de la entidad de la base de datos transaccion con sus respectivos atributos,
//gerando sus Getter y Setter para la manipulacion de los datos.
@Entity
@Table(name = "transaccion")
public class Transaccion {

    // la estregia es un id unico por tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "emisor")
    private String emisor;

    @Column(name = "Fecha_hora")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

    @Column(name = "tipo")
    private String tipo;

    public Transaccion(Long id, Double monto, String emisor, LocalDateTime fecha, Cuenta cuenta, String tipo) {
        this.id = id;
        this.monto = monto;
        this.emisor = emisor;
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.tipo = tipo;
    }

    public Transaccion(Double monto, String emisor, LocalDateTime fecha, Cuenta cuenta, String tipo) {
        this.monto = monto;
        this.emisor = emisor;
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.tipo = tipo;
    }

    public Transaccion(Double monto, String emisor, LocalDateTime fecha, Cuenta cuenta) {
        this.monto = monto;
        this.emisor = emisor;
        this.fecha = fecha;
        this.cuenta = cuenta;
    }

    public Transaccion() {

    }

    // Getter y Setter para manipular los datos

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return this.monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuenta() {
        return this.cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getEmisor() {
        return this.emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
