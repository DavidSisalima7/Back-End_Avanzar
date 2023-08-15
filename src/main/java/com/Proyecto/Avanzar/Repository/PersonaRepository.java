package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Persona;
import com.Proyecto.Avanzar.Models.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    @Query(value = "SELECT * FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona WHERE u.username = :username", nativeQuery = true)
    public Persona obtenerPersona(String username);
    
    @Query(value = "SELECT * FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona WHERE u.id = :id", nativeQuery = true)
    public Persona obtenerPersonaUsuario(Long id);

    public Persona findByCedula(String cedula);

    @Query(value = "SELECT * FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona JOIN usuario_rol usr ON usr.rol_rol_id = 2 and usr.usuario_id = u.id", nativeQuery = true)
    List<Persona> listarResponsable();
//
//    select DISTINCT p.cedula, p.primer_nombre, p.primer_apellido
//    from persona p join usuarios ON usuarios.persona_id_persona = p.id_persona
//    join usuario_rol usr ON usr.rol_rol_id =3 and usr.usuario_id = usuarios.id

}
