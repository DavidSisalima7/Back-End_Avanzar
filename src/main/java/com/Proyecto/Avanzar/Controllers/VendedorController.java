package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Vendedor;
import com.Proyecto.Avanzar.Services.service.VendedorService;
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
@RequestMapping("/api/vendedor")
public class VendedorController {
    @Autowired
    VendedorService vendedorService;
@PostMapping("/registrar")
    public ResponseEntity<Vendedor> crear(@RequestBody Vendedor r) {
        try {
            return new ResponseEntity<>(vendedorService.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Vendedor>> obtenerLista() {
        try {
            return new ResponseEntity<>(vendedorService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        return vendedorService.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Vendedor> actualizar(@PathVariable Long id,@RequestBody Vendedor p) {
        Vendedor subscripcion = vendedorService.findById(id);
        if (subscripcion == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                subscripcion.setNombreEmprendimiento(p.getNombreEmprendimiento());
                subscripcion.setListapublicaciones(p.getListapublicaciones());
                subscripcion.setUsuario(p.getUsuario());
                subscripcion.setDetalleSubscripcion(p.getDetalleSubscripcion());
                
                return new ResponseEntity<>(vendedorService.save(subscripcion), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}