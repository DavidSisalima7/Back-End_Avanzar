package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Productos;
import com.Proyecto.Avanzar.Models.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiciosRepository extends JpaRepository<Servicios, Long> {
    @Query("SELECT s FROM Servicios s " +
            "WHERE s.idServicio = :idServicio and s.estado = true")
    public Servicios BuscarServicioActivoxId(Long idServicio);

    @Query("SELECT s FROM Servicios s ORDER BY s.estado desc")
    List<Servicios> FiltradoServxEstadoInactivo();
    @Query("SELECT s FROM Servicios s ORDER BY s.estado asc")
    List<Servicios> FiltradoServxEstadoActivo();
}
