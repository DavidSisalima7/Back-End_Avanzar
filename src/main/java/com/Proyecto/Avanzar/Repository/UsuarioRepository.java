package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        @Query(value = "SELECT *\n" +
                        "\tFROM usuarios WHERE username = :username", nativeQuery = true)
        public Usuario findByUsername(String username);

        @Query(value = "SELECT *\n" +
                        "\tFROM usuarios WHERE username = :username", nativeQuery = true)
        public Usuario findAllByUsername(String username);

        @Query(value = "SELECT *\n" +
                        "\tFROM usuarios WHERE enabled = true AND visible=true", nativeQuery = true)
        public abstract List<Usuario> listar();


        @Query(value = "SELECT * FROM usuarios WHERE username=:user", nativeQuery = true)
        public Usuario buscarId(String user);


        @Query("SELECT u FROM Usuario u " +
                "JOIN Persona per ON u.persona.id_persona = per.id_persona " +
                "JOIN UsuarioRol us ON us.usuario.id = u.id " +
                "WHERE us.rol.rolId = 2")
        List<Usuario> findAllUsuariosWithPersonaAndRol();

}
