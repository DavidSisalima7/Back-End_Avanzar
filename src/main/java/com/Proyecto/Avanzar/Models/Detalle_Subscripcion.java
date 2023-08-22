package com.Proyecto.Avanzar.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="detalle_subscripcion")
public class Detalle_Subscripcion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle_subscripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado;


    //Relacion Subscripcion
    @ManyToOne(fetch = FetchType.EAGER)
    private Subscripcion subscripcion;

    @OneToOne(fetch = FetchType.EAGER)
    private Vendedor vendedor;



}
