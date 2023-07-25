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
@Table(name = "publicaciones")
public class Publicaciones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;
    private String Titulo;
    private String descripcion;
    private boolean estado;
    private Date fecha;
    private Date Hora;
    //Relaciones
    //Relacion Vendedor
    //Relacion Categoria
    //Relacion Producto
    //Relacion Servicio
    //Relacion TipoPost no se ha definido aun
}
