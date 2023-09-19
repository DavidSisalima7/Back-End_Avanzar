package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
  //  List<Comentarios> findByPublicacionId(Long idPublicacion);
}
