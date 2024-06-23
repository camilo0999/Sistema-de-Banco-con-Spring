package com.sistema.banco.dto;

public class CorreoDto {

    private String destinatario;

    private String sujeto;

    private String mensaje;

    public CorreoDto(String destinatario, String sujeto, String mensaje) {
        this.destinatario = destinatario;
        this.sujeto = sujeto;
        this.mensaje = mensaje;
    }

    public CorreoDto() {
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

    @Override
    public String toString() {
        return "{" +
                " destinatario='" + getDestinatario() + "'" +
                ", sujeto='" + getSujeto() + "'" +
                ", mensaje='" + getMensaje() + "'" +
                "}";
    }

}
