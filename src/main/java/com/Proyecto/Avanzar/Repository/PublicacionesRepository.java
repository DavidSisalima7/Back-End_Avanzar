package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Publicaciones;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublicacionesRepository extends JpaRepository<Publicaciones, Long> {

    //Query para listar publicaciones activas
    @Query(value = "SELECT *\n" +
            "\tFROM publicaciones WHERE estado=true order by fecha_publicacion desc", nativeQuery = true)
    public List<Publicaciones> listar();

    @Query(value = "SELECT * FROM publicaciones WHERE productos_id_producto IS NOT NULL AND visible=true AND vendedor_id_vendedor = :vendedorId ORDER BY fecha_publicacion DESC", nativeQuery = true)
    public List<Publicaciones> listarPublicacionesConProductos(@Param("vendedorId") Long vendedorId);

    @Query(value = "SELECT * FROM publicaciones WHERE productos_id_producto IS NOT NULL AND visible=true order by fecha_publicacion desc", nativeQuery = true)
    public List<Publicaciones> listarPublicacionesConProductos();
    
    //contar las publicaciones visibles 
    Long countByVendedor_idVendedorAndVisibleTrue(Long vendedorId);
    
    //contar las publicacioens con estado activo 
    Long countByVendedor_idVendedorAndEstadoTrueAndVisibleTrue(Long vendedorId);

  //otra forma 
  //Long countByFechaPublicacionBetweenAndVendedor_Id(Date fechaInicio, Date fechaFin, Long vendedorId);
    @Query(value = "SELECT * FROM publicaciones WHERE servicios_id_servicio IS NOT NULL AND estado=true AND vendedor_id_vendedor = :vendedorId ORDER BY fecha_publicacion DESC", nativeQuery = true)
    public List<Publicaciones> listarPublicacionesConServicios(@Param("vendedorId") Long vendedorId);


    @Query(value = "SELECT * FROM publicaciones WHERE servicios_id_servicio IS NOT NULL AND estado=true ORDER BY fecha_publicacion DESC", nativeQuery = true)
    public List<Publicaciones> listarServicios();

    @Query(value = "SELECT * FROM publicaciones WHERE productos_id_producto IS NOT NULL AND estado=true ORDER BY fecha_publicacion DESC", nativeQuery = true)
    public List<Publicaciones> listarProductos();
    
    @Query(value="SELECT * FROM publicaciones p JOIN destacados d ON p.id_publicacion=d.publicaciones_id_publicacion AND d.usuario_id = :idUser AND estado=true", nativeQuery = true)
    List<Publicaciones> listarDestacados(@Param("idUser")Long id);
}
