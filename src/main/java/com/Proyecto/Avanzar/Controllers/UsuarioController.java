package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Rol;
import com.Proyecto.Avanzar.Models.Usuario;
import com.Proyecto.Avanzar.Models.UsuarioRol;
import com.Proyecto.Avanzar.Repository.UsuarioRepository;
import com.Proyecto.Avanzar.Services.service.RolService;
import com.Proyecto.Avanzar.Services.service.StorageService;
import com.Proyecto.Avanzar.Services.service.UsuarioRolService;
import com.Proyecto.Avanzar.Services.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private  StorageService storageService;
    @Autowired
    private  HttpServletRequest request;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioRepository uR;
    @Autowired
    private UsuarioRolService userrol;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //Metodo para que admita un archivo
    @PostMapping("upload")
    public Map<String,String> uploadFile(@RequestParam("file") MultipartFile multipartFile){
        String path=storageService.store(multipartFile);
        String host=request.getRequestURL().toString().replace(request.getRequestURI(),"");
        String url= ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();
        return Map.of("url",url);
    }


    //Metodo para recuperar la imagen desde el sistema de archivos
    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws  IOException {
        Resource file=storageService.loadAsResource(filename);
        String contentType= Files.probeContentType(file.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE,contentType)
                .body(file);

    }

    @PostConstruct
    public void init() {
        Rol usuario1 = new Rol(1L, "ADMIN");
        Rol usuario2 = new Rol(2L, "RESPONSABLE_VENTAS");
        Rol usuario3 = new Rol(3L, "EMPRENDEDORA");
        Rol usuario4 = new Rol(4L, "CLIENTE");

        rolService.save(usuario1);
        rolService.save(usuario2);
        rolService.save(usuario3);
        rolService.save(usuario4);
    }
    @PostMapping("/registrar/{rolId}")
    public ResponseEntity<Usuario> crear(@RequestBody Usuario r, @PathVariable Long rolId) {
        try {
            if (usuarioService.obtenerUsuario(r.getUsername()) == null) {
                // Buscar el rol por ID
                Rol rol = rolService.findById(rolId);
                r.setPassword(this.bCryptPasswordEncoder.encode(r.getPassword()));
                r.setVisible(true);
                // Crear un nuevo UsuarioRol y establecer las referencias correspondientes
                UsuarioRol usuarioRol = new UsuarioRol();
                usuarioRol.setUsuario(r);
                usuarioRol.setRol(rol);
                // Agregar el UsuarioRol a la lista de roles del usuario
                r.getUsuarioRoles().add(usuarioRol);
                // Guardar el usuario en la base de datos
                // Usuario nuevoUsuario = usuarioService.save(r);
                return new ResponseEntity<>(usuarioService.save(r), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> obtenerLista() {
        try {

            return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerUsuario(username);
    }

    @GetMapping("/buscaruser/{username}")
    public Usuario obtenerIdUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerId(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.delete(usuarioId);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarCliente(@PathVariable Long id, @RequestBody Usuario p) {
        Usuario usu = usuarioService.findById(id);
        UsuarioRol urol=userrol.findByUsuario_UsuarioId(id);
        if (usu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                usu.setPassword(this.bCryptPasswordEncoder.encode(p.getPassword()));
                usu.setListacomentarios(p.getListacomentarios());
                usu.setPersona(p.getPersona());
                usu.setListavendedor(p.getListavendedor());
                usu.setListaLikes(p.getListaLikes());
                usu.setUsuarioRoles(p.getUsuarioRoles());
                return new ResponseEntity<>(usuarioService.save(usu), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    @PutMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Usuario a = usuarioService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(usuarioService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }


}
