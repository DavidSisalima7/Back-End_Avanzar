package com.Proyecto.Avanzar.Services.service;
import com.Proyecto.Avanzar.Models.Comentarios;
import com.Proyecto.Avanzar.Models.ComentariosDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ComentariosService extends GenericService<Comentarios, Long>{
    
    ResponseEntity<List<ComentariosDto>> listCommentPost(Long idPubli, int page);
}
