package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Comentarios;
import java.util.List;

import com.Proyecto.Avanzar.Models.Productos;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
    
    @Query("SELECT NEW Comentarios(c.fecha,c.texto, u.avatar,u.name) FROM Comentarios c JOIN Usuario u ON c.usuario.id=u.id AND c.publicaciones.idPublicacion =  :idPubli ORDER BY c.fecha DESC")
    List<Comentarios> listCommentPost(@Param("idPubli") Long idPublicacion, Pageable pageable);

    @Query("SELECT c FROM Comentarios c JOIN Usuario us ON c.usuario.id=us.id AND us.id = :id")
    List<Comentarios> ComentariosxUsuario(@Param("id") Long id);

}
