package com.Proyecto.Avanzar.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscripcion")
public class Subscripcion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubscripcion;
    private String nombreSubscripcion;
    private double precio;
    private int NumPublicaciones;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean estado;

    //Relacion
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "subscripcion")
    private Set<Vendedor> listavendedor = new HashSet<>();
}
