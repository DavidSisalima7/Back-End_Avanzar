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
@Table(name = "categoriaServicio",uniqueConstraints = {@UniqueConstraint(columnNames = "nombreCategoria")})
public class CategoriaServicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaServicio;
    private String nombreCategoria;
    private String descripcion;
    private boolean estado;

    //Relacion

}
