package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Persona;
import com.Proyecto.Avanzar.Repository.Persona_repository;
import com.Proyecto.Avanzar.Services.service.Persona_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class Persona_ServiceImpl extends GenericServiceImpl<Persona, Long> implements Persona_Service {
    @Autowired
    private Persona_repository repository;
    @Override
    public CrudRepository<Persona, Long > getDao() {

        return repository;
    }
    @Override
    public Persona obtenerPersona(String username) {
        return repository.obtenerPersona(username);
    }

    @Override
    public Persona obtenerPersonaPorIdUsuario(Long id) {
        return repository.obtenerPersonaUsuario(id);    
    }

    @Override
    public Persona findByCedula(String cedula) {
        return repository.findByCedula(cedula);
    }

}
