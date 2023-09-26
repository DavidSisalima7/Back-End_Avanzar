package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Destacados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.Proyecto.Avanzar.Services.service.DestacadoService;
import com.Proyecto.Avanzar.Repository.DestacadoRepository;

@Service
public class DestacadoServiceImpl extends GenericServiceImpl<Destacados, Long> implements DestacadoService {
    @Autowired
    private DestacadoRepository likesDao;

    @Override
    public CrudRepository<Destacados, Long> getDao() {
        return likesDao;
    }

    public boolean existeDestacado(Long idPublicacion, Long id) {
        // consulta en la base de datos para verificar si existe un destacado con los IDs proporcionados.
        return likesDao.existsByPublicacionesIdPublicacionAndUsuarioId(idPublicacion, id);
    }
}
