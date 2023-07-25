package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Publicaciones;
import com.Proyecto.Avanzar.Services.service.PublicacionesService;
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
@RequestMapping("/api/publicaciones")
public class PublicacionesController {
    @Autowired
    PublicacionesService publicacionesService;
    @PostMapping("/registrar")
    public ResponseEntity<Publicaciones> crear(@RequestBody Publicaciones r) {
        try {
            return new ResponseEntity<>(publicacionesService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Publicaciones>> obtenerLista() {
        try {
            return new ResponseEntity<>(publicacionesService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Publicaciones publicaciones) {
        return publicacionesService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Publicaciones> actualizar(@PathVariable Long id,@RequestBody Publicaciones p) {
        Publicaciones productos = publicacionesService.findById(id);
        if (productos == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                productos.setTitulo(p.getTitulo());
                productos.setDescripcion(p.getDescripcion());
                productos.setFecha(p.getFecha());
                productos.setHora(p.getHora());
                productos.setEstado(p.isEstado());

                return new ResponseEntity<>(publicacionesService.save(productos), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
