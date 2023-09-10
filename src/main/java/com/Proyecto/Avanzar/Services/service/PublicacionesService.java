package com.Proyecto.Avanzar.Services.service;
import com.Proyecto.Avanzar.Models.Publicaciones;
import java.util.Date;

public interface PublicacionesService extends GenericService<Publicaciones, Long>{

    Long countPubli(Date fechaInicio,Date FechaFin,Long idVenFK);
}
