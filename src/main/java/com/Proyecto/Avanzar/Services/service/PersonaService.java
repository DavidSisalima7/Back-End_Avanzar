package com.Proyecto.Avanzar.Services.service;

import com.Proyecto.Avanzar.Models.Persona;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonaService extends GenericService<Persona, Long>{
    @Query(value = "SELECT * FROM persona p JOIN usuarios u ON p.id_persona = u.persona_id_persona WHERE u.username = :username", nativeQuery = true)
    public Persona obtenerPersona(String username);
    
    public Persona obtenerPersonaPorIdUsuario(Long id);
    public Persona findByCedula(String cedula);

    public Persona findByCorreo(String correo);
}
