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
@Table(name = "categoriaProducto",uniqueConstraints = {@UniqueConstraint(columnNames = "nombreCategoria")})
public class CategoriaProducto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaProducto;
    private String nombreCategoria;
    private String descripcion;
    private boolean estado;

    //Relacion


}
