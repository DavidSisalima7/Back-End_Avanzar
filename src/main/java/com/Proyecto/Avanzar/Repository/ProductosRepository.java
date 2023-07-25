package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
}
