package com.Proyecto.Avanzar.Services.service;



import com.Proyecto.Avanzar.Models.Usuario;
import com.Proyecto.Avanzar.Models.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);
}
