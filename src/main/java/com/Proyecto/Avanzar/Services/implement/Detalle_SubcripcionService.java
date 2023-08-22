package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Detalle_Subscripcion;
import com.Proyecto.Avanzar.Repository.Detalle_SubscripcionRepository;
import com.Proyecto.Avanzar.Services.service.Detalle_SubscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class Detalle_SubcripcionService extends GenericServiceImpl<Detalle_Subscripcion,Long> implements Detalle_SubscripcionService {

    @Autowired
    private Detalle_SubscripcionRepository repository;
    @Override
    public CrudRepository<Detalle_Subscripcion, Long> getDao() {
        return repository;
    }
}
