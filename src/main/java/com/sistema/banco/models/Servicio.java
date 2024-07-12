package com.sistema.banco.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "categoria")
    private String categoira;

    @Lob // Indica que el campo puede contener un objeto grande (large object)
    @Column(name = "imagen", columnDefinition = "LONGTEXT")
    private String rutaImagen;

    public Servicio(Long id, String nombre, Double precio, String categoira, String rutaImagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoira = categoira;
        this.rutaImagen = rutaImagen;
    }

    public Servicio(String nombre, Double precio, String categoira, String rutaImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoira = categoira;
        this.rutaImagen = rutaImagen;
    }

    public Servicio(String nombre, String categoira, String rutaImagen) {
        this.nombre = nombre;
        this.categoira = categoira;
        this.rutaImagen = rutaImagen;
    }

    public Servicio() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoira() {
        return this.categoira;
    }

    public void setCategoira(String categoira) {
        this.categoira = categoira;
    }

    public String getRutaImagen() {
        return this.rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

}
