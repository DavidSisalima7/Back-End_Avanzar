package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Likes;
import com.Proyecto.Avanzar.Services.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/likes")
public class LikesController {
    @Autowired
    LikesService likesService;
    @PostMapping("/registrar")
    public ResponseEntity<Likes> crear(@RequestBody Likes r) {
        try {
            return new ResponseEntity<>(likesService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Likes>> obtenerLista() {
        try {
            return new ResponseEntity<>(likesService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Likes likes) {
        return likesService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Likes> actualizar(@PathVariable Long id,@RequestBody Likes p) {
        Likes likes = likesService.findById(id);
        if (likes == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                likes.setFecha(p.getFecha());


                return new ResponseEntity<>(likesService.save(likes), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
