package com.Proyecto.Avanzar.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "Roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nombre")
        })
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    private String nombre;

    public Rol(Long idRol, String nombre, Set<Usuario> usuarioRoles) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.usuarioRoles = usuarioRoles;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<Usuario> usuarioRoles =new HashSet<>();
}
