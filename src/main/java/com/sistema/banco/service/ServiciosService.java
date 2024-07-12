package com.sistema.banco.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sistema.banco.models.Servicio;

public interface ServiciosService {

    public List<Servicio> lista();

    public void guardarServicio(Servicio servicio, MultipartFile file);

    public Servicio buscarServicio(Long id);

    public void eliminarServicio(Long id);

    public String buscarImagen(MultipartFile file);

    public void editarServicio(Long id, Servicio servicio, MultipartFile file);

}
