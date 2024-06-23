package com.sistema.banco.service;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class CorreoServiceImp implements CorreoServie {

    @Value("${email.sender}")
    private String emailUser;

    private final JavaMailSender mailSender;

    public CorreoServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarCorreo(String destinatario, String sujeto, String mensaje) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailUser);
        mailMessage.setTo(destinatario);
        mailMessage.setSubject(sujeto);
        mailMessage.setText(mensaje);
        mailSender.send(mailMessage);

    }

    @Override
    public void enviarCorreoArchivo(String destinatario, String sujeto, String mensaje, File file) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true,
                    StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(emailUser);
            mimeMessageHelper.setTo(destinatario);
            mimeMessageHelper.setSubject(sujeto);
            mimeMessageHelper.setText(mensaje);
            mimeMessageHelper.addAttachment(file.getName(), file);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
