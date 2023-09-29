package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Publicaciones;
import com.Proyecto.Avanzar.Repository.PublicacionesRepository;
import com.Proyecto.Avanzar.Services.service.PublicacionesService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicacionesServiceImpl extends GenericServiceImpl<Publicaciones, Long> implements PublicacionesService {
    @Autowired
    private PublicacionesRepository publicacionesDao;

    @Override
    public CrudRepository<Publicaciones, Long> getDao() {
        return publicacionesDao;
    }

    @Override
    public Long countPubli(Long idVenFK) {
    
        return publicacionesDao.countByVendedor_idVendedorAndVisibleTrueAndEstadoTrue(idVenFK);
    
    }

    @Override
    public Long countPubliEstatus(Long idVenFk) {
        return publicacionesDao.countByVendedor_idVendedorAndVisibleTrueAndEstadoTrue(idVenFk);
    }
}
