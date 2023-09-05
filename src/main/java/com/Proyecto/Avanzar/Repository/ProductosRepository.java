package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Productos;
import com.Proyecto.Avanzar.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
    @Query("SELECT p FROM Productos p " +
            "WHERE p.idProducto = :idProducto and p.estadoProducto = true")
    public Productos BuscarProductoActivoxId(Long idProducto);

    @Query("SELECT p FROM Productos p ORDER BY p.estadoProducto desc")
    List<Productos> FiltradoProdxEstadoInactivo();
    @Query("SELECT p FROM Productos p ORDER BY p.estadoProducto asc")
    List<Productos> FiltradoProdxEstadoActivo();
}
