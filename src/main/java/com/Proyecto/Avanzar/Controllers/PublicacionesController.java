package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.*;
import com.Proyecto.Avanzar.Repository.PublicacionesRepository;
import com.Proyecto.Avanzar.Services.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.ElementCollection;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionesController {

    @Autowired
    private StorageService storageService;
    @Autowired
    PublicacionesService publicacionesService;

    @Autowired
    ProductosService productosService;

    @Autowired
    ServiciosService serviciosService;
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    CategoriaProductoService categoriaProductoService;
    @Autowired
    VendedorService vendedorService;

    @Autowired
    PublicacionesRepository publicacionesRepository;

    //Metodo para registrar una publicacion con sus respectivas fotos
    @PostMapping("/registrarConFoto")
    public ResponseEntity<Publicaciones> crear(
            @RequestPart("publicacion") String publicacionJson,
            @RequestPart(value = "files", required = false) List<MultipartFile> multipartFiles,
            HttpServletRequest request
    ) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Publicaciones r = objectMapper.readValue(publicacionJson, Publicaciones.class);
            System.out.println("Publicacion convertida JSON: " + r.toString());


            if (multipartFiles != null && !multipartFiles.isEmpty()) {
                List<String> urls = new ArrayList<>();

                for (MultipartFile multipartFile : multipartFiles) {
                    String path = storageService.store(multipartFile);
                    String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
                    String url = ServletUriComponentsBuilder
                            .fromHttpUrl(host)
                            .path("/api/publicaciones/")
                            .path(path)
                            .toUriString();
                    urls.add(url);
                }

                r.setImagenes(urls);
            } else {
                List<String> imagenesPredefinidas = new ArrayList<>();
                r.setImagenes(imagenesPredefinidas);
            }
            return new ResponseEntity<>(publicacionesService.save(r), HttpStatus.CREATED);


        } catch (Exception e) {
            System.out.println("Error al convertir el JSON de la publicación.");
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Metodo para recuperar la imagen desde el sistema de archivos
    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
        Resource file = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);

    }


    @PostMapping("/registrar")
    public ResponseEntity<Publicaciones> registrarPublicacion(@RequestBody Publicaciones publicacion) {
        try {
            return new ResponseEntity<>(publicacionesService.save(publicacion), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lista que devuelve el tiempo transcurrido de cada publicacion despues de ser publicada
    @GetMapping("/listar")
    public ResponseEntity<List<Publicaciones>> obtenerListas() {
        try {
            List<Publicaciones> publicaciones = publicacionesRepository.listar();

            // Itera sobre la lista de publicaciones y calcula el tiempo transcurrido para cada una
            for (Publicaciones publicacion : publicaciones) {
                String tiempoTranscurrido = TiempoTranscurridoUtil.calcularTiempoTranscurridoFormateado(publicacion.getFechaPublicacion());
                publicacion.setTiempoTranscurrido(tiempoTranscurrido);
            }

            return new ResponseEntity<>(publicaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listaPublicacionesXProductos/{vendedorId}")
    public List<Publicaciones> getPublicacionesProductos(@PathVariable Long vendedorId) {
        return publicacionesRepository.listarPublicacionesConProductos(vendedorId);
    }

    @GetMapping("/listaPublicacionesXServicios/{vendedorId}")
    public List<Publicaciones> getPublicacionesServicios(@PathVariable Long vendedorId) {
        return publicacionesRepository.listarPublicacionesConServicios(vendedorId);
    }


    @PutMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarlogic(@PathVariable Long id) {
        Publicaciones a = publicacionesService.findById(id);
        if (a == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                a.setVisible(false);
                return new ResponseEntity<>(publicacionesService.save(a), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Publicaciones> actualizar(@PathVariable Long id,@RequestBody Publicaciones p) {
        Publicaciones publicaciones = publicacionesService.findById(id);
        if (publicaciones == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                publicaciones.setTituloPublicacion(p.getTituloPublicacion());
                publicaciones.setDescripcionPublicacion(p.getDescripcionPublicacion());
                publicaciones.setEstado(p.isEstado());
                publicaciones.setVendedor(p.getVendedor());

                return new ResponseEntity<>(publicacionesService.save(publicaciones), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

    /*
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        try {
            publicacionesService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Publicaciones> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<Publicaciones>(publicacionesService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
