package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Comentarios;
import com.Proyecto.Avanzar.Repository.ComentariosRepository;
import com.Proyecto.Avanzar.Services.service.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ComentariosServiceImpl extends GenericServiceImpl<Comentarios, Long> implements ComentariosService {
    @Autowired
    private ComentariosRepository comentariosDao;

    @Override
    public CrudRepository<Comentarios, Long> getDao() {
        return null;
    }
}
