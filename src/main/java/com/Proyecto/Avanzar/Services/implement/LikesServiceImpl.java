package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Likes;
import com.Proyecto.Avanzar.Repository.LikesRepository;
import com.Proyecto.Avanzar.Services.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl extends GenericServiceImpl<Likes, Long> implements LikesService {
    @Autowired
    private LikesRepository likesDao;

    @Override
    public CrudRepository<Likes, Long> getDao() {
        return likesDao;
    }
}
