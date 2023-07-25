package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Publicaciones;
import com.Proyecto.Avanzar.Repository.PublicacionesRepository;
import com.Proyecto.Avanzar.Services.service.PublicacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class PublicacionesServiceImpl extends GenericServiceImpl<Publicaciones, Long> implements PublicacionesService {
    @Autowired
    private PublicacionesRepository publicacionesDao;

    @Override
    public CrudRepository<Publicaciones, Long> getDao() {
        return publicacionesDao;
    }
}
