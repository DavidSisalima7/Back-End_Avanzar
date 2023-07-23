package com.Proyecto.Avanzar.Services.service;

import com.Proyecto.Avanzar.Models.Usuario;

import java.util.List;

public interface UsuarioService extends GenericService<Usuario, Long> {
    public Usuario obtenerUsuario(String username);

    public Usuario obtenerId(String username);

    public Usuario findAllByUsername(String username);

}
