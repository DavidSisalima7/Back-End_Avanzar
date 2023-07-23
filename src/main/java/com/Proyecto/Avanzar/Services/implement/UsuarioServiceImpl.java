package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Usuario;
import com.Proyecto.Avanzar.Repository.UsuarioRepository;
import com.Proyecto.Avanzar.Services.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public CrudRepository<Usuario, Long> getDao() {
        return usuarioRepository;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }
    @Override
    public Usuario findAllByUsername(String username) {
        return usuarioRepository.findAllByUsername(username);
    }


    @Override
    public Usuario obtenerId(String username) {
        return usuarioRepository.buscarId(username);
    }

}