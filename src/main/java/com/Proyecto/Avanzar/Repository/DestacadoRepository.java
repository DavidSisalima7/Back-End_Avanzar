package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Destacados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestacadoRepository extends JpaRepository<Destacados, Long> {
    boolean existsByPublicacionesIdPublicacionAndUsuarioId(Long publicacionId, Long usuarioId);

}
