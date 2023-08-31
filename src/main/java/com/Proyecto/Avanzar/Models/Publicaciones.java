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
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publicaciones")
public class Publicaciones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;
    private String tituloPublicacion;
    private String descripcionPublicacion;
    private boolean estado;
    private Date fechaPublicacion;

    //private boolean visible;

    @ElementCollection
    private List<String> imagenes;
    //Relaciones
    //Relacion Vendedor
    @ManyToOne(fetch = FetchType.EAGER)
    private Vendedor vendedor;

    //Relacion Categoria
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    //Relacion Producto
    @ManyToOne(fetch = FetchType.EAGER)
    private Productos productos;

    //Relacion Servicio
    @ManyToOne(fetch = FetchType.EAGER)
    private Servicios servicios;

    //Relacion TipoPost no se ha definido aun

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publicaciones")
    @JsonIgnore
    private Set<Comentarios> listacomentarios = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publicaciones")
    @JsonIgnore
    private Set<Likes> listalikes = new HashSet<>();


}
