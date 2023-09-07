package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {


    @Query(value = "SELECT * FROM vendedor WHERE usuario_id = :userId", nativeQuery = true)
    List<Vendedor> findByUsuarioId(@Param("userId") Long userId);
}
