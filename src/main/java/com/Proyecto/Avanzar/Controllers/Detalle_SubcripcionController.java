package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Detalle_Subscripcion;
import com.Proyecto.Avanzar.Models.Subscripcion;
import com.Proyecto.Avanzar.Services.service.Detalle_SubscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/detalleSubscripcion")
public class Detalle_SubcripcionController {

    @Autowired
    Detalle_SubscripcionService subscripcionService;

    @PostMapping("/registrar")
    public ResponseEntity<Detalle_Subscripcion> crear(@RequestBody Detalle_Subscripcion  r) {
        try {
            return new ResponseEntity<>(subscripcionService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Detalle_Subscripcion>> obtenerLista() {
        try {
            return new ResponseEntity<>(subscripcionService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Detalle_Subscripcion subscripcion) {
        return subscripcionService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Detalle_Subscripcion> actualizar(@PathVariable Long id,@RequestBody Detalle_Subscripcion p) {
        Detalle_Subscripcion subscripcion = subscripcionService.findById(id);
        if (subscripcion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                subscripcion.setDescripcion(p.getDescripcion());
                subscripcion.setFechaInicio(p.getFechaInicio());
                subscripcion.setFechaFin(p.getFechaFin());
                subscripcion.setSubscripcion(p.getSubscripcion());
                subscripcion.setVendedor(p.getVendedor());
                return new ResponseEntity<>(subscripcionService.save(subscripcion), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
