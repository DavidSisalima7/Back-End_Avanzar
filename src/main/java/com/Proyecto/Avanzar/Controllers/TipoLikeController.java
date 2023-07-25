package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.TipoLike;
import com.Proyecto.Avanzar.Services.service.TipoLikeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/tipoLike")
public class TipoLikeController {
    @Autowired
    TipoLikeService tipoLikeService;

@PostMapping("/registrar")
    public ResponseEntity<TipoLike> crear(@RequestBody TipoLike r) {
        try {
            return new ResponseEntity<>(tipoLikeService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TipoLike>> obtenerLista() {
        try {
            return new ResponseEntity<>(tipoLikeService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody TipoLike tipoLike) {
        return tipoLikeService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TipoLike> actualizar(@PathVariable Long id,@RequestBody TipoLike p) {
        TipoLike tipoLike = tipoLikeService.findById(id);
        if (tipoLike == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                tipoLike.setMeGusta(p.getMeGusta());
                tipoLike.setNoMeGusta(p.getNoMeGusta());
               
               

                return new ResponseEntity<>(tipoLikeService.save(tipoLike), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}