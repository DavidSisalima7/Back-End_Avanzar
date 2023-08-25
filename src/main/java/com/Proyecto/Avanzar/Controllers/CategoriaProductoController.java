package com.Proyecto.Avanzar.Controllers;


import com.Proyecto.Avanzar.Models.CategoriaProducto;

import com.Proyecto.Avanzar.Services.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/categoriaProducto")
public class CategoriaProductoController {
    @Autowired
    CategoriaProductoService categoriaProductoService;

    @PostMapping("/registrar")
    public ResponseEntity<CategoriaProducto> crear(@RequestBody CategoriaProducto r) {
        try {
            return new ResponseEntity<>(categoriaProductoService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaProducto>> obtenerLista() {
        try {
            return new ResponseEntity<>(categoriaProductoService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody CategoriaProducto categoriaProducto) {
        return categoriaProductoService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CategoriaProducto> actualizar(@PathVariable Long id,@RequestBody CategoriaProducto p) {
        CategoriaProducto categoriaProducto = categoriaProductoService.findById(id);
        if (categoriaProducto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                categoriaProducto.setDescripcion(p.getDescripcion());
                categoriaProducto.setEstado(p.isEstado());
                return new ResponseEntity<>(categoriaProductoService.save(categoriaProducto), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}

