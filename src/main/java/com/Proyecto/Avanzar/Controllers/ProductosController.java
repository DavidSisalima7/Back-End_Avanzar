package com.Proyecto.Avanzar.Controllers;


import com.Proyecto.Avanzar.Models.Productos;
import com.Proyecto.Avanzar.Services.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/productos")
public class ProductosController {
    @Autowired
    ProductosService productosService;

    @PostMapping("/registrar")
    public ResponseEntity<Productos> crear(@RequestBody Productos r) {
        try {
            return new ResponseEntity<>(productosService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Productos>> obtenerLista() {
        try {
            return new ResponseEntity<>(productosService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Productos productos) {
        return productosService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Productos> actualizar(@PathVariable Long id,@RequestBody Productos p) {
        Productos productos = productosService.findById(id);
        if (productos == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                productos.setNombreProducto(p.getNombreProducto());
                productos.setPrecioProducto(p.getPrecioProducto());
                productos.setCantidadDisponible(p.getCantidadDisponible());
                productos.setEstado(p.isEstado());
                productos.setListaCategoriaprod(p.getListaCategoriaprod());
                productos.setListapublicaciones(p.getListapublicaciones());
                return new ResponseEntity<>(productosService.save(productos), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
