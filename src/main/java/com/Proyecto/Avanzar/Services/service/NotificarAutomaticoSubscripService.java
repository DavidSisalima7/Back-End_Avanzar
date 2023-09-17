/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Avanzar.Services.service;

import com.Proyecto.Avanzar.Models.dto.EmailDto;
import com.Proyecto.Avanzar.Repository.PersonaRepository;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author gt618
 */
@Service
public class NotificarAutomaticoSubscripService {

    @Autowired
    private JavaMailSender mail;

    @Autowired
    private PersonaRepository repoPer;

    @Scheduled(cron = "0 0 17 * * *")
    public void notificarSubscripCaducar() {
        List<String> correos = repoPer.obtenerCorreosNotificacionCadu();
        EmailDto e = new EmailDto();

        if (!e.sendAlertSuscrip()) {
            System.out.println("error al cargar html notificar");
        }

        for (String correo : correos) {

            MimeMessage basEmail = mail.createMimeMessage();
            try {

                MimeMessageHelper contEmail = new MimeMessageHelper(basEmail, true, "utf-8");
                contEmail.setSubject("Renovacion de suscripción");
                contEmail.setFrom("FundacionAvanzarW@gmail.com");
                contEmail.setTo(correo);
                contEmail.setText(e.getText(), true);

                mail.send(basEmail);

            } catch (MessagingException ex) {
                System.out.println("Error al enviar email renovación suscripción");
            }
        }
    }

}
