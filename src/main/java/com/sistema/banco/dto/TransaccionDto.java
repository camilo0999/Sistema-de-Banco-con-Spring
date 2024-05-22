package com.sistema.banco.dto;

import java.time.LocalDateTime;

import com.sistema.banco.models.Cuenta;

public class TransaccionDto {

    private Long id;

    private Double monto;

    private String tipo;

    private LocalDateTime fecha;

    private Cuenta cuenta;

    public TransaccionDto(Long id, Double monto, String tipo, LocalDateTime fecha, Cuenta cuenta) {
        this.id = id;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = fecha;
        this.cuenta = cuenta;
    }

    public TransaccionDto(Double monto, String tipo, LocalDateTime fecha, Cuenta cuenta) {
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = fecha;
        this.cuenta = cuenta;
    }

    public TransaccionDto() {

    }

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

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

}
