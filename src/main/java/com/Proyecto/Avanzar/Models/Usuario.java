package com.Proyecto.Avanzar.Models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Setter
@Getter
@Table(name = "Usuario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "correo")
        })
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String correo;
    private String password;

    public Usuario(Long idUsuario, String correo, String password, Persona persona, Rol rol) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.password = password;
        this.persona = persona;
        this.rol = rol;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Persona persona;

 @ManyToOne
    private Rol rol;

}
