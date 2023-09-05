package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Persona;
import com.Proyecto.Avanzar.Services.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/persona")
public class Persona_Controller {
    @Autowired
    PersonaService Service;

    @PostMapping("/registrar")
    public ResponseEntity<Persona> crear(@RequestBody Persona r) {
        try {
            return new ResponseEntity<>(Service.save(r), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Persona>> obtenerLista() {
        try {
            return new ResponseEntity<>(Service.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/buscarpersona/{username}")
    public ResponseEntity<Persona> obtenerPersona(@PathVariable("username") String username) {
        try {
            return new ResponseEntity<>(Service.obtenerPersona(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByCedula/{cedula}")
    public ResponseEntity<Persona> findByCedula(@PathVariable("cedula") String cedula) {
        try {
            return new ResponseEntity<>(Service.findByCedula(cedula), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Persona persona) {
        return Service.delete(id);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Persona> actualizar(@PathVariable Long id,@RequestBody Persona p) {
        Persona persona = Service.findById(id);
        if (persona == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                persona.setDireccion(p.getDireccion());
                persona.setCorreo(p.getCorreo());
                persona.setCelular(p.getCelular());
                persona.setPrimer_nombre(p.getPrimer_nombre());
                persona.setPrimer_apellido(p.getPrimer_apellido());
                persona.setListausuarios(p.getListausuarios());
                return new ResponseEntity<>(Service.save(persona), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
    ///////////////actualizar 2.0
    @PutMapping("/actualizar1/{cedula}")
    public ResponseEntity<Persona> actualizar1(@PathVariable String cedula,@RequestBody Persona p) {
        System.out.println("entrar ");
        Persona persona = Service.findByCedula(cedula);
        if (persona == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                persona.setDireccion(p.getDireccion());
                persona.setCorreo(p.getCorreo());
                persona.setCelular(p.getCelular());
                persona.setPrimer_nombre(p.getPrimer_nombre());
                persona.setSegundo_nombre(p.getSegundo_nombre());
                persona.setPrimer_apellido(p.getPrimer_apellido());
                persona.setSegundo_apellido(p.getSegundo_apellido());
                persona.setGenero(p.getGenero());
                persona.setNacionalidad(p.getNacionalidad());
                persona.setFecha_nacimiento(p.getFecha_nacimiento());
                persona.setDescripcion(p.getDescripcion());
                persona.setEstado(p.isEstado());
                persona.setListausuarios(p.getListausuarios());
                
              
                return new ResponseEntity<>(Service.save(persona), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
    @GetMapping("/buscarpersonaId/{id}")
    public ResponseEntity<Persona> obtenerPersonaUsuarioId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(Service.obtenerPersonaPorIdUsuario(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}