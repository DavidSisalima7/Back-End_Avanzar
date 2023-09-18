package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Comentarios;
import com.Proyecto.Avanzar.Models.Publicaciones;
import com.Proyecto.Avanzar.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {

    List<Comentarios> findByPublicacionesAndUsuario(Publicaciones publicacion );
}
