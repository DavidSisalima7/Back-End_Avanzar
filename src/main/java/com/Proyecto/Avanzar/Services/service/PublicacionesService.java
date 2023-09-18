package com.Proyecto.Avanzar.Services.service;
import com.Proyecto.Avanzar.Models.Publicaciones;
import com.Proyecto.Avanzar.Models.TiempoTranscurridoUtil;

import java.util.Date;

public interface PublicacionesService extends GenericService<Publicaciones, Long>{

    Long countPubli(Long idVenFK);
    
    Long countPubliEstatus(Long idVenFk);

}
