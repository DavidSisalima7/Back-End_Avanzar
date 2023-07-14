package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Usuario;
import com.Proyecto.Avanzar.Repository.UsuarioDao;
import com.Proyecto.Avanzar.Services.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp extends GenericServiceImpl<Usuario,Long> implements UsuarioService {
    @Autowired
    UsuarioDao usuarioDao;
    @Override
    public CrudRepository<Usuario, Long> getDao() {
        return usuarioDao;
    }

}
