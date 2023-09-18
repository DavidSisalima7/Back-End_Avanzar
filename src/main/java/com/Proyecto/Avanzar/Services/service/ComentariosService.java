package com.Proyecto.Avanzar.Services.service;
import com.Proyecto.Avanzar.Models.Comentarios;
import com.Proyecto.Avanzar.Models.Publicaciones;
import com.Proyecto.Avanzar.Models.Usuario;

import java.util.List;

public interface ComentariosService extends GenericService<Comentarios, Long>{

    List<Comentarios> findByPublicacionAndUsuario(Publicaciones publicacion);
}
