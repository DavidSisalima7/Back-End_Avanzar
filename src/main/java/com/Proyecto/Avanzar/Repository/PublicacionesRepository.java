package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublicacionesRepository extends JpaRepository<Publicaciones, Long> {

    //Query para listar publicaciones activas
    @Query(value = "SELECT *\n" +
            "\tFROM publicaciones WHERE visible=true", nativeQuery = true)
    public List<Publicaciones> listar();

    @Query(value = "SELECT * FROM publicaciones WHERE servicios_id_servicio IS NOT NULL AND visible=true", nativeQuery = true)
    public List<Publicaciones> listarPublicacionesConServicios();

    @Query(value = "SELECT * FROM publicaciones WHERE productos_id_producto IS NOT NULL AND visible=true", nativeQuery = true)
    public List<Publicaciones> listarPublicacionesConProductos();

}
