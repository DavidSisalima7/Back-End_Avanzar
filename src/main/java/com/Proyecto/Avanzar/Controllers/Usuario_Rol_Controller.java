package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Persona;
import com.Proyecto.Avanzar.Models.Rol;
import com.Proyecto.Avanzar.Models.UsuarioRol;
import com.Proyecto.Avanzar.Services.service.RolService;
import com.Proyecto.Avanzar.Services.service.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuariorol")
@CrossOrigin("*")
public class Usuario_Rol_Controller {
    @Autowired
    private UsuarioRolService usuarioService;
    private RolService roleservice;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/listarol")
    public ResponseEntity<List<Rol>> obtenerRoles() {
        try {
            return new ResponseEntity<>(roleservice.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/lista_us_rol")
    public ResponseEntity<List<UsuarioRol>> obtenerLista() {
        try {
            System.out.println(usuarioService.findByAll());
            return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/actualizar/{usuarioRolId}")
    public ResponseEntity<UsuarioRol> actualizarRol(@RequestBody UsuarioRol usuarioRol, @PathVariable Long usuarioRolId) {
        try {
            UsuarioRol usuarioRolExistente = usuarioService.findById(usuarioRolId);
            if (usuarioRolExistente != null) {
                String nuevaContraseña = usuarioRol.getUsuario().getPassword();
                // Actualizar la contraseña en el usuario existente
                if (!nuevaContraseña.equals(usuarioRolExistente.getUsuario().getPassword())) {
                    usuarioRolExistente.getUsuario().setPassword(bCryptPasswordEncoder.encode(nuevaContraseña));
                }
                usuarioRolExistente.setRol(usuarioRol.getRol());
                UsuarioRol usuarioRolActualizado = usuarioService.save(usuarioRolExistente);
                return new ResponseEntity<>(usuarioRolActualizado, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
