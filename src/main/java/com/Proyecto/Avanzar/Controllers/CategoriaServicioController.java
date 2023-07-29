package com.Proyecto.Avanzar.Controllers;


import com.Proyecto.Avanzar.Models.CategoriaServicio;
import com.Proyecto.Avanzar.Services.service.CategoriaServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/categoriaServicio")
public class CategoriaServicioController {
    @Autowired
    CategoriaServicioService categoriaServicioService;
    @PostMapping("/registrar")
    public ResponseEntity<CategoriaServicio> crear(@RequestBody CategoriaServicio r) {
        try {
            return new ResponseEntity<>(categoriaServicioService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaServicio>> obtenerLista() {
        try {
            return new ResponseEntity<>(categoriaServicioService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody CategoriaServicio categoriaServicio) {
        return categoriaServicioService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CategoriaServicio> actualizar(@PathVariable Long id,@RequestBody CategoriaServicio p) {
        CategoriaServicio categoriaServicio = categoriaServicioService.findById(id);
        if (categoriaServicio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                categoriaServicio.setNombreCategoria(p.getNombreCategoria());
                categoriaServicio.setDescripcion(p.getDescripcion());
                categoriaServicio.setEstado(p.isEstado());

                return new ResponseEntity<>(categoriaServicioService.save(categoriaServicio), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
