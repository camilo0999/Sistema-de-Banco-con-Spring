package com.sistema.banco.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compras")
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private Date fecha;

    @JoinColumn(name = "nombre_cliente")
    private String clienteNombre;

    @ManyToMany
    @JoinTable(name = "compra_servicio", joinColumns = @JoinColumn(name = "compra_id"), inverseJoinColumns = @JoinColumn(name = "servicio_id"))
    private Set<Servicio> servicios = new HashSet<>();

    @JoinColumn(name = "detalle_compra")
    private String detalle;

    public Compras(Long id, Date fecha, String clienteNombre, Set<Servicio> servicios, String detalle) {
        this.id = id;
        this.fecha = fecha;
        this.clienteNombre = clienteNombre;
        this.servicios = servicios;
        this.detalle = detalle;
    }

    public Compras(Date fecha, String clienteNombre, Set<Servicio> servicios, String detalle) {
        this.fecha = fecha;
        this.clienteNombre = clienteNombre;
        this.servicios = servicios;
        this.detalle = detalle;
    }

    public Compras() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getClienteNombre() {
        return this.clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public Set<Servicio> getServicios() {
        return this.servicios;
    }

    public void setServicios(Set<Servicio> servicios) {
        this.servicios = servicios;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}
