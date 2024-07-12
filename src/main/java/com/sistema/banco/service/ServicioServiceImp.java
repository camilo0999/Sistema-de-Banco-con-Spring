package com.sistema.banco.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.banco.models.Servicio;
import com.sistema.banco.repository.ServiciosRepository;

@Service
public class ServicioServiceImp implements ServiciosService {

    private static Logger logger = LoggerFactory.getLogger(ServicioServiceImp.class);

    private final ServiciosRepository serviciosRepository;

    @Value("${app.imgService.dir}")
    private String uploadDir;

    public ServicioServiceImp(ServiciosRepository serviciosRepository) {
        this.serviciosRepository = serviciosRepository;
    }

    @Override
    public List<Servicio> lista() {
        return serviciosRepository.findAll();
    }

    @Override
    public void guardarServicio(Servicio servicio, MultipartFile file) {
        try {

            String imagen = buscarImagen(file);

            servicio.setRutaImagen(imagen);

            serviciosRepository.save(servicio);
            logger.info("El servicio {} fue guardado correctamente", servicio.getNombre());
        } catch (DataAccessException dae) {
            logger.error("Error al guardar servicio: {}", dae.getMessage(), dae);
        } catch (Exception e) {
            logger.error("Error inesperado al guardar servicio: {}", e.getMessage(), e);
        }
    }

    @Override
    public Servicio buscarServicio(Long id) {
        try {
            Optional<Servicio> servicio = serviciosRepository.findById(id);
            if (servicio.isPresent()) {
                return servicio.get();
            } else {
                logger.warn("El servicio con id {} no fue encontrado", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error inesperado al buscar servicio: {}", e.getMessage(), e);
            throw new RuntimeException("Error inesperado al buscar servicio", e);
        }
    }

    @Override
    public void eliminarServicio(Long id) {
        try {
            if (serviciosRepository.existsById(id)) {
                serviciosRepository.deleteById(id);
                logger.info("El servicio con id {} fue eliminado correctamente", id);
            } else {
                logger.warn("El servicio con id {} no fue encontrado y no se pudo eliminar", id);
            }
        } catch (Exception e) {
            logger.error("Error inesperado al eliminar servicio: {}", e.getMessage(), e);
            throw new RuntimeException("Error inesperado al eliminar servicio", e);
        }
    }

    @Override
    public String buscarImagen(MultipartFile file) {
        try {
            // Obtener el nombre limpio del archivo
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Verificar si el nombre de archivo es válido
            if (fileName.contains("..")) {
                throw new Exception("Nombre de archivo no válido: " + fileName);
            }

            // Generar un nombre único para el archivo
            String newFileName = UUID.randomUUID().toString() + "_" + fileName;

            // Crear la carpeta de carga si no existe
            Path dirPath = Paths.get(uploadDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Guardar el archivo en la carpeta de carga
            Path filePath = dirPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath);

            // Devolver la URL de la imagen
            return "/imgService/" + newFileName; // Ejemplo: "/uploads/12345_imagen.jpg"
        } catch (Exception e) {
            // Manejar cualquier error
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void editarServicio(Long id, Servicio servicio, MultipartFile file) {
        try {

            Servicio servicio2 = buscarServicio(id);

            servicio2.setCategoira(servicio.getCategoira());

            servicio2.setNombre(servicio.getNombre());

            servicio2.setPrecio(servicio.getPrecio());

            if (file != null) {
                servicio2.setRutaImagen(buscarImagen(file));
            }

            logger.info("SE REALIZO CON EXITO LA ACTUALIZACION");

        } catch (Exception e) {
            logger.error("SUCEDIO UN ERROR EN EDITAR SERVICIO: ", e);
        }
    }

}
