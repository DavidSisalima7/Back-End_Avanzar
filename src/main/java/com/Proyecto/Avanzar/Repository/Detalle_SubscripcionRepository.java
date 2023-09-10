package com.Proyecto.Avanzar.Repository;

import com.Proyecto.Avanzar.Models.Detalle_Subscripcion;
import com.Proyecto.Avanzar.Models.Publicaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalle_SubscripcionRepository extends JpaRepository<Detalle_Subscripcion, Long> {








    //no borrar puede ser util en el futuro 

    @Query("SELECT NEW Detalle_Subscripcion( d.fechaInicio,d.fechaFin, v.idVendedor ,d.subscripcion.NumPublicaciones) FROM Detalle_Subscripcion d"
            + " JOIN Vendedor v ON  d.vendedor.idVendedor=v.idVendedor JOIN Usuario u ON v.usuario.id =u.id AND u.username= :user")
    Detalle_Subscripcion dataSuscripUser(@Param("user") String username);

   /*@Query("SELECT NEW Detalle_Subscripcion(d.subscripcion.idSubscripcion as idDetalle_subscripcion ,d.fechaInicio, d.fechaFin) \n" +
"FROM Detalle_Subscripcion d JOIN d.vendedor v JOIN v.usuario u WHERE u.username = :user")
        Detalle_Subscripcion dataSuscripUser(@Param("user") String username);*/
}
