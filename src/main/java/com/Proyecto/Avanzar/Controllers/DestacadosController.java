package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Destacados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.Proyecto.Avanzar.Services.service.DestacadoService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/likes")
public class DestacadosController {

    @Autowired
    DestacadoService likesService;

    @PostMapping("/registrar")
    public ResponseEntity<Destacados> crear(@RequestBody Destacados r) {
        try {
            return new ResponseEntity<>(likesService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Destacados>> obtenerLista() {
        try {
            return new ResponseEntity<>(likesService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Destacados likes) {
        return likesService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Destacados> actualizar(@PathVariable Long id, @RequestBody Destacados p) {
        Destacados likes = likesService.findById(id);
        if (likes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                likes.setFecha(p.getFecha());
                likes.setPublicaciones(p.getPublicaciones());
                likes.setIdDestacado(p.getIdDestacado());
                likes.setUsuario(p.getUsuario());
                likes.setEstadoDestacado(p.isEstadoDestacado());

                return new ResponseEntity<>(likesService.save(likes), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
