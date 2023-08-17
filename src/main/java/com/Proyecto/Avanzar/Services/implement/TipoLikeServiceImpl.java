package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.TipoLike;
import com.Proyecto.Avanzar.Repository.TipoLikeRepository;
import com.Proyecto.Avanzar.Services.service.TipoLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoLikeServiceImpl extends GenericServiceImpl<TipoLike, Long> implements TipoLikeService {
    @Autowired
    private TipoLikeRepository tipoLikeDao;

    @Override
    public CrudRepository<TipoLike, Long> getDao() {
        return tipoLikeDao;
    }
}
