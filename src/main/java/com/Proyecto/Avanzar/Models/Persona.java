package com.Proyecto.Avanzar.Models;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "Persona",
        uniqueConstraints = {
          @UniqueConstraint(columnNames = "correo")
        })
public class Persona {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idPersona;
        private String primerNombre;
        private String segundoNombre;
        private String primerApellido;
        private String segundoApellido;
        private String direccion;
        private String celular;
        private String genero;
        private Date fechaNacimiento;
        private String correo;

        public Persona(Long idPersona, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String direccion, String celular, String genero, Date fechaNacimiento, String correo) {
                this.idPersona = idPersona;
                this.primerNombre = primerNombre;
                this.segundoNombre = segundoNombre;
                this.primerApellido = primerApellido;
                this.segundoApellido = segundoApellido;
                this.direccion = direccion;
                this.celular = celular;
                this.genero = genero;
                this.fechaNacimiento = fechaNacimiento;
                this.correo = correo;
        }
        @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "persona")
        private Set<Usuario> usuariosRoles =new HashSet<>();
}
