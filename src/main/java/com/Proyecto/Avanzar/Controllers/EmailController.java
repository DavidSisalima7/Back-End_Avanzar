/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Avanzar.Controllers;


import com.Proyecto.Avanzar.Models.dto.EmailDto;
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
    public ResponseEntity<Boolean> enviarCorreo(@RequestBody EmailDto e) {

        String mensaje="Su código de Verificación es:";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(e.getTo());
        email.setFrom("reservcompany5b@gmail.com");
        email.setSubject(e.getSubject());
        email.setText(mensaje +" código generado"+e.getText());
        
        
        try{
            mail.send(email);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }catch(MailException exc) {
             return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
