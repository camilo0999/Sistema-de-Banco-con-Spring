package com.sistema.banco.controller;

import com.sistema.banco.dto.CorreoDto;
import com.sistema.banco.dto.CorreoFileDto;
import com.sistema.banco.dto.UsernameDto;
import com.sistema.banco.service.CorreoServie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/correo")
public class CorreoController {

    private final CorreoServie correoService;

    @Autowired
    public CorreoController(CorreoServie correoService) {
        this.correoService = correoService;
    }

    @PostMapping("/enviarMensaje")
    public ResponseEntity<?> enviarCorreoSimple(@RequestBody CorreoDto correoDto) {
        // Logging en lugar de System.out.println
        Logger logger = LoggerFactory.getLogger(CorreoController.class);
        logger.info("Mensaje recibido: {}", correoDto.toString());

        // Validar los datos del DTO
        if (correoDto.getDestinatario() == null || correoDto.getSujeto() == null || correoDto.getMensaje() == null) {
            return ResponseEntity.badRequest().body("Los campos destinatario, sujeto y mensaje son obligatorios.");
        }

        correoService.enviarCorreo(correoDto.getDestinatario(), correoDto.getSujeto(), correoDto.getMensaje());

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("estado", "Enviado");

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/enviarCorreoArchivo")
    public ResponseEntity<?> enviarCorreoConArchivo(@ModelAttribute CorreoFileDto correoFileDto) {
        try {
            MultipartFile multipartFile = correoFileDto.getFile();
            if (multipartFile == null || multipartFile.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo es obligatorio.");
            }

            String fileName = multipartFile.getOriginalFilename();
            Path path = Paths.get("src/mail/resources/files/" + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            File file = path.toFile();

            correoService.enviarCorreoArchivo(correoFileDto.getDestinatario(), correoFileDto.getSujeto(),
                    correoFileDto.getMensaje(), file);

            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("estado", "Enviado");
            respuesta.put("archivo", fileName);

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            // Logging en lugar de System.out.println
            Logger logger = LoggerFactory.getLogger(CorreoController.class);
            logger.error("Error al enviar el correo con el archivo: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error al enviar el correo con el archivo: " + e.getMessage());
        }
    }

    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperarContrasena(@RequestBody UsernameDto usernameDto) {
        try {
            // Validar el dato del DTO
            if (usernameDto.getUsername() == null || usernameDto.getUsername().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre de usuario es obligatorio.");
            }

            System.out.println("LLEGO EL CORREO");

            // Llama al servicio para cambiar la contraseña
            correoService.cambioContrasena(usernameDto.getUsername());

            // Crear una respuesta exitosa
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "¡Este proceso se realizó con éxito!");

            // Retornar una respuesta exitosa
            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            // Logging en lugar de System.out.println
            Logger logger = LoggerFactory.getLogger(CorreoController.class);
            logger.error("Error al recuperar la contraseña: {}", e.getMessage());
            // Manejar posibles excepciones y retornar una respuesta de error
            Map<String, String> respuestaError = new HashMap<>();
            respuestaError.put("error", "Error al recuperar la contraseña: " + e.getMessage());
            return ResponseEntity.status(500).body(respuestaError);
        }
    }

}
