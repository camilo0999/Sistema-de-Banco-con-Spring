package com.sistema.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.banco.dto.CorreoDto;
import com.sistema.banco.dto.CorreoFileDto;
import com.sistema.banco.service.CorreoServie;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/correo")
public class CorreoController {

    @Autowired
    private final CorreoServie correoService;

    public CorreoController(CorreoServie correoService) {
        this.correoService = correoService;
    }

    @PostMapping("/enviarMensaje")
    public ResponseEntity<?> enviarCorreoSimple(@RequestBody CorreoDto correoDto) {

        System.out.println("Mensaje recibido: " + correoDto.toString());

        correoService.enviarCorreo(correoDto.getDestinatario(), correoDto.getSujeto(), correoDto.getMensaje());

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("estado", "Enviado");

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/enviarCorreoArchivo")
    public ResponseEntity<?> enviarCorreoConArchivo(@ModelAttribute CorreoFileDto correoFileDto) {
        try {
            MultipartFile multipartFile = correoFileDto.getFile();
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
            return ResponseEntity.status(500).body("Error al enviar el correo con el archivo: " + e.getMessage());
        }
    }
}
