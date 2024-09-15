package com.sistema.banco.service;

import java.io.File;

public interface CorreoServie {

    public void enviarCorreo(String destinatario, String sujeto, String mensaje);

    public void enviarCorreoArchivo(String destinatario, String sujeto, String mensaje, File file);

    public void cambioContrasena(String destinatario);

}
