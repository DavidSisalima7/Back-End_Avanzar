/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Avanzar.Models.dto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class EmailDto {

    private String to;
    private String from;
    private String subject;
    private String text;

    public boolean cargarHtml(String codigo) {

        try {

            Path path = Paths.get("src/main/java/com/Proyecto/Avanzar/Models/dto/FormatCodigo.html").toAbsolutePath();
            String htmlContent = new String(Files.readAllBytes(path));
            htmlContent = htmlContent.replace("%CODIGO%", codigo);
            this.text = htmlContent;
            return true;
        } catch (IOException ex) {
            Logger.getLogger(EmailDto.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al cargar el HTML");
            return false;
        }
    }

}
