package com.sistema.banco.dto;

import org.springframework.web.multipart.MultipartFile;

public class CorreoFileDto {

    private String destinatario;

    private String sujeto;

    private String mensaje;

    private MultipartFile file;

    public CorreoFileDto(String destinatario, String sujeto, String mensaje, MultipartFile file) {
        this.destinatario = destinatario;
        this.sujeto = sujeto;
        this.mensaje = mensaje;
        this.file = file;
    }

    public String getDestinatario() {
        return this.destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getSujeto() {
        return this.sujeto;
    }

    public void setSujeto(String sujeto) {
        this.sujeto = sujeto;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public MultipartFile getFile() {
        return this.file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "{" +
                " destinatario='" + getDestinatario() + "'" +
                ", sujeto='" + getSujeto() + "'" +
                ", mensaje='" + getMensaje() + "'" +
                ", file='" + getFile() + "'" +
                "}";
    }

}
