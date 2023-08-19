/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.Avanzar.Models.dto;

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

}
