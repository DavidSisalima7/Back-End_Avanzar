package com.Proyecto.Avanzar.Services.service;
import com.Proyecto.Avanzar.Models.Comentarios;

import java.util.List;

public interface ComentariosService extends GenericService<Comentarios, Long>{
    Comentarios saveComentario(Comentarios comentario); // Guardar un comentario
    void deleteComentario(Long comentarioId); // Eliminar un comentario por su ID
    //List<Comentarios> findByPublicacionId(Long publicacionId); // Buscar comentarios por ID de publicación
}
