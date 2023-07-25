package com.Proyecto.Avanzar.Controllers;


import com.Proyecto.Avanzar.Models.Comentarios;
import com.Proyecto.Avanzar.Services.service.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/comentarios")
public class ComentariosController {
    @Autowired
    ComentariosService comentariosService;
    @PostMapping("/registrar")
    public ResponseEntity<Comentarios> crear(@RequestBody Comentarios r) {
        try {
            return new ResponseEntity<>(comentariosService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Comentarios>> obtenerLista() {
        try {
            return new ResponseEntity<>(comentariosService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Comentarios comentarios) {
        return comentariosService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Comentarios> actualizar(@PathVariable Long id,@RequestBody Comentarios p) {
        Comentarios comentarios = comentariosService.findById(id);
        if (comentarios == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                comentarios.setTexto(p.getTexto());
                comentarios.setFecha(p.getFecha());

                return new ResponseEntity<>(comentariosService.save(comentarios), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
 //el comentario si se debe eliminar