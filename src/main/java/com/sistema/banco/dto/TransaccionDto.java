package com.sistema.banco.dto;

import java.time.LocalDateTime;

import com.sistema.banco.models.Cuenta;

//De acuerdo al patro de diseño Dto estoy mappiando la entidad Transaccion del la base de datos para enviar los datos al cliente.
public class TransaccionDto {

    private Long id;

    private Double monto;

    private String emisor;

    private LocalDateTime fecha;

    private Cuenta cuenta;

    private String tipo;

    public TransaccionDto(Long id, Double monto, String emisor, LocalDateTime fecha, Cuenta cuenta, String tipo) {
        this.id = id;
        this.monto = monto;
        this.emisor = emisor;
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.tipo = tipo;
    }

    public TransaccionDto(Double monto, String emisor, LocalDateTime fecha, Cuenta cuenta, String tipo) {
        this.monto = monto;
        this.emisor = emisor;
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.tipo = tipo;
    }

    public TransaccionDto(Double monto, String emisor, LocalDateTime fecha, Cuenta cuenta) {
        this.monto = monto;
        this.emisor = emisor;
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

    public void setEmisor(String emisor) {
        this.emisor = emisor;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

}
