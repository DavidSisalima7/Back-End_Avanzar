package com.Proyecto.Avanzar.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")
public class Productos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String nombreProducto;
    private double precioProducto;
    private int cantidadDisponible;
    private boolean estado;

    //Relaciones
    //Relacion CategoriaProducto
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "productos")
    private Set<CategoriaProducto> listaCategoriaprod = new HashSet<>();

    //Relacion con publicaciones
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "productos")
    @JsonIgnore
    private Set<Publicaciones> listapublicaciones = new HashSet<>();
}
