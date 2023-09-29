package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Publicaciones;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

public interface PublicacionesRepository extends JpaRepository<Publicaciones, Long> {

    //Query para listar publicaciones activas
    @Query(value = "SELECT *\n" +
            "\tFROM publicaciones WHERE visible=true", nativeQuery = true)
    public List<Publicaciones> listar();

    @Query(value = "SELECT * FROM publicaciones WHERE productos_id_producto IS NOT NULL AND visible=true AND vendedor_id_vendedor = :vendedorId ORDER BY fecha_publicacion DESC", nativeQuery = true)
    public List<Publicaciones> listarPublicacionesConProductos(@Param("vendedorId") Long vendedorId);

    @Query(value = "SELECT * FROM publicaciones WHERE productos_id_producto IS NOT NULL AND visible=true order by fecha_publicacion desc", nativeQuery = true)
    public List<Publicaciones> listarPublicacionesConProductos();
    
    //contar las publicaciones visibles 
    Long countByVendedor_idVendedorAndVisibleTrueAndEstadoTrue(Long vendedorId);

  //otra forma 
  //Long countByFechaPublicacionBetweenAndVendedor_Id(Date fechaInicio, Date fechaFin, Long vendedorId);
    @Query(value = "SELECT * FROM publicaciones WHERE servicios_id_servicio IS NOT NULL AND visible=true AND vendedor_id_vendedor = :vendedorId ORDER BY fecha_publicacion DESC", nativeQuery = true)
    public List<Publicaciones> listarPublicacionesConServicios(@Param("vendedorId") Long vendedorId);


    @Query(value = "SELECT * FROM publicaciones WHERE servicios_id_servicio IS NOT NULL AND visible=true ORDER BY fecha_publicacion DESC", nativeQuery = true)
    public List<Publicaciones> listarServicios();

    @Query(value = "SELECT * FROM publicaciones WHERE productos_id_producto IS NOT NULL AND visible=true ORDER BY fecha_publicacion DESC", nativeQuery = true)
    public List<Publicaciones> listarProductos();
    
    @Query(value="SELECT * FROM publicaciones p JOIN destacados d ON p.id_publicacion=d.publicaciones_id_publicacion AND d.usuario_id = :idUser", nativeQuery = true)
    List<Publicaciones> listarDestacados(@Param("idUser")Long id);
    
   //paso una lista de id vendedor para ver quien tiene mas de 3 publicacione activas
    @Query("SELECT p.vendedor.idVendedor FROM Publicaciones p WHERE p.vendedor.idVendedor IN :idVen AND p.estado=true AND p.visible=true GROUP BY p.vendedor.idVendedor HAVING COUNT(p)>3")
    List<Long> listarIdVenDesacPublicFree(@Param("idVen")List<Long> idVendedor );
    
    //listar publicaciones para ser desactivadas 
    @Query("SELECT p.idPublicacion FROM Publicaciones p WHERE  p.vendedor.idVendedor = :idVen AND p.estado=true AND p.visible=true ORDER BY p.fechaPublicacion DESC")
    List<Long> listarIdPublicDesac(@Param("idVen")Long idVendedor);
    
    //Desactivas las publicaciones que cuando se acaba la suscripcion
    @Modifying
    @Transactional
    @Query(value = "UPDATE Publicaciones  SET estado = false WHERE id_publicacion IN (:idPubli)", nativeQuery = true)
    void updateStateSuscripCaducado(@Param("idPubli") List<Long> idPublicacion);      
}
