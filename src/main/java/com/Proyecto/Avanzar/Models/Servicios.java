package com.Proyecto.Avanzar.Models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servicios")
public class Servicios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;
    private String nombreServicio;
    private String descripcionServicio;
    private double precioServicio;
    private boolean estado;

    //Relaciones
    //Relacion Categoria
    //Relacion Categoria Servicio
}