package com.sistema.banco.dto;

public class ServiciosDto {

    private Long id;

    private String nombre;

    private Double precio;

    private String categoira;

    private String rutaImagen;

    public ServiciosDto(Long id, String nombre, Double precio, String categoira, String rutaImagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoira = categoira;
        this.rutaImagen = rutaImagen;
    }

    public ServiciosDto(String nombre, Double precio, String categoira, String rutaImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoira = categoira;
        this.rutaImagen = rutaImagen;
    }

    public ServiciosDto(String nombre, String categoira, String rutaImagen) {
        this.nombre = nombre;
        this.categoira = categoira;
        this.rutaImagen = rutaImagen;
    }

    public ServiciosDto() {
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
