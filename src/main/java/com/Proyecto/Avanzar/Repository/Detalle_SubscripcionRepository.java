package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Detalle_Subscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalle_SubscripcionRepository extends JpaRepository<Detalle_Subscripcion,Long> {
}
