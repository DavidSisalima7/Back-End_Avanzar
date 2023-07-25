package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Services.service.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/comentarios")
public class ComentariosController {
    @Autowired
    ComentariosService comentariosService;
}
// el comentario si se debe eliminar