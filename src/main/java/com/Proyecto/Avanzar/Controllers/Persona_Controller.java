package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Persona;
import com.Proyecto.Avanzar.Models.Usuario;
import com.Proyecto.Avanzar.Services.service.PersonaService;
import com.Proyecto.Avanzar.Services.service.UsuarioService;
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
    @Autowired
    UsuarioService usuarioS;

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
//Persona
    @PutMapping("/actualizarP/{id}")
    public ResponseEntity<Usuario> actualizarUser(@PathVariable Long id, @RequestBody Usuario p) {
        Usuario usuario = usuarioS.findById(id);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                if(p.getName() != null){
                    usuario.setName(p.getName());

                }
                return new ResponseEntity<>(usuarioS.save(usuario), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
//User
//    @PutMapping("/actualizar/{id}")
//    public ResponseEntity<Persona> actualizar(@PathVariable Long id,@RequestBody Persona p) {
//        Persona persona = Service.findById(id);
//        if (persona == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            try {
//                persona.setDireccion(p.getDireccion());
//                persona.setCorreo(p.getCorreo());
//                persona.setCelular(p.getCelular());
//                persona.setPrimer_nombre(p.getPrimer_nombre());
//                persona.setPrimer_apellido(p.getPrimer_apellido());
//                persona.setSegundo_apellido(p.getSegundo_apellido());
//                persona.setListausuarios(p.getListausuarios());
//                return new ResponseEntity<>(Service.save(persona), HttpStatus.CREATED);
//            } catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        }
//    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Persona> actualizar(@PathVariable Long id, @RequestBody Persona p) {
        Persona persona = Service.findById(id);
        if (persona == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                // Actualizar solo los campos que se proporcionan en la solicitud
                if (p.getDireccion() != null) {
                    persona.setDireccion(p.getDireccion());
                }
                if (p.getCorreo() != null) {
                    persona.setCorreo(p.getCorreo());
                }
                if (p.getCelular() != null) {
                    persona.setCelular(p.getCelular());
                }
                if (p.getNacionalidad() != null) {
                    persona.setNacionalidad(p.getNacionalidad());
                }
                if (p.getPrimer_nombre() != null) {
                    persona.setPrimer_nombre(p.getPrimer_nombre());
                }
                if (p.getPrimer_apellido() != null) {
                    persona.setPrimer_apellido(p.getPrimer_apellido());
                }
                if (p.getSegundo_apellido() != null) {
                    persona.setSegundo_apellido(p.getSegundo_apellido());
                }
                if (p.getSegundo_nombre() != null) {
                    persona.setSegundo_nombre(p.getSegundo_nombre());
                }
                if (p.getFecha_nacimiento() != null) {
                    persona.setFecha_nacimiento(p.getFecha_nacimiento());
                }
//                if (p.isEstado() != false) {
                    persona.setEstado(p.isEstado());
//                }

                if (p.getGenero() != null) {
                    persona.setGenero(p.getGenero());
                }
                if (p.getListausuarios() != null) {
                    persona.setListausuarios(p.getListausuarios());
                }

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