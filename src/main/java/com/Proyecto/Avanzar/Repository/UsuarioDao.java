package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuario,Long> {
}
