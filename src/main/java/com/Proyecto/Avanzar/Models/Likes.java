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
@Table(name = "likes")
public class Likes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLike;
    private String fecha;
    //Relaciones
    //Relacion Publicacion
    @ManyToOne(fetch = FetchType.EAGER)
    private Publicaciones publicaciones;

    //Relacion Usuario
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    //Relacion Tipo Like
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoLike tipoLikes;

}
