/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.dto.EmailDto;
import java.security.SecureRandom;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private JavaMailSender mail;

    @PostMapping("/sentCodeVerification")
    public ResponseEntity<EmailDto> enviarCorreo(@RequestBody EmailDto e) {

        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(e.getSubject());
        email.setTo(e.getTo());
        email.setFrom("reservcompany5b@gmail.com");
        email.setText(ramdomCode(9,7));
        
        try {
            mail.send(email);
            e.setText(email.getText());
            return new ResponseEntity<>(e, HttpStatus.OK);
        } catch (MailException exc) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String ramdomCode(int max, int min) {
        Random ran = new Random();

        int let = ran.nextInt((max - min) + 1) + min;
        //updateFechaPasByEmail(email.getTo(),new Date)
        //generar un numero aleatorio de 7 a 13 cifras
        String carac = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        SecureRandom sec = new SecureRandom();
        StringBuilder ramBui = new StringBuilder();

        for (int i = 0; i < let; i++) {
            int randomIndex = sec.nextInt(carac.length());
            char randomChar = carac.charAt(randomIndex);
            ramBui.append(randomChar);
        }
        return ramBui.toString();
    }
}
