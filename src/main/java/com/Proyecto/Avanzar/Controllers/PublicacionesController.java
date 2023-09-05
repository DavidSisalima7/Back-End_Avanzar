package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.*;
import com.Proyecto.Avanzar.Repository.PublicacionesRepository;
import com.Proyecto.Avanzar.Services.service.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import javax.persistence.ElementCollection;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionesController {
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

    @PostMapping("/registrar")
    public ResponseEntity<Publicaciones> crear(@RequestBody Publicaciones request) {
        try {
            // Crear una nueva instancia de Publicaciones a partir de la solicitud
            Publicaciones nuevaPublicacion = new Publicaciones();

            nuevaPublicacion.setEstado(true);
            nuevaPublicacion.setTituloPublicacion("Nueva Publicacion");
            nuevaPublicacion.setVisible(true);
            // Obtener el producto desde el servicio de productos (supongamos que tienes un servicio llamado productosService)
            Productos producto = new Productos();
            producto.setNombreProducto("Nuevo Producto");
            producto.setDescripcionProducto("Descripción del producto");
            producto.setEstadoProducto(true);
            producto.setMiniaturaProducto("");
            Productos nuevoProducto = productosService.save(producto);

            Categoria categoria = categoriaService.findById(1L);

            nuevaPublicacion.setCategoria(categoria);
            // Asignar el producto a la nueva publicación
            nuevaPublicacion.setProductos(nuevoProducto);

            Date fecha = new Date();
            fecha = new Date(fecha.getTime());
            nuevaPublicacion.setFechaPublicacion(fecha);

            List<String> imagenesPredefinidas = new ArrayList<>();
            nuevaPublicacion.setImagenes(imagenesPredefinidas);


            // Guardar la nueva publicación
            Publicaciones nuevaPublicacionGuardada = publicacionesService.save(nuevaPublicacion);

            return new ResponseEntity<>(nuevaPublicacionGuardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registrarServicios")
    public ResponseEntity<Publicaciones> crearServicios(@RequestBody Publicaciones request) {
        try {
            // Crear una nueva instancia de Publicaciones a partir de la solicitud
            Publicaciones nuevaPublicacion = new Publicaciones();

            nuevaPublicacion.setEstado(true);
            nuevaPublicacion.setTituloPublicacion("Nueva Publicacion");
            nuevaPublicacion.setVisible(true);
            // Obtener el producto desde el servicio de productos (supongamos que tienes un servicio llamado productosService)
            Servicios servicios = new Servicios();
            servicios.setNombreServicio("Nuevo Servicio");
            servicios.setDescripcionServicio("Descripción del servicio");
            servicios.setEstado(true);
            servicios.setMiniaturaServicio("");
            Servicios nuevoServicio = serviciosService.save(servicios);

            Categoria categoria = categoriaService.findById(1L);

            nuevaPublicacion.setCategoria(categoria);
            // Asignar el producto a la nueva publicación
            nuevaPublicacion.setServicios(nuevoServicio);

            Date fecha = new Date();
            fecha = new Date(fecha.getTime());
            nuevaPublicacion.setFechaPublicacion(fecha);

            List<String> imagenesPredefinidas = new ArrayList<>();
            nuevaPublicacion.setImagenes(imagenesPredefinidas);


            // Guardar la nueva publicación
            Publicaciones nuevaPublicacionGuardada = publicacionesService.save(nuevaPublicacion);

            return new ResponseEntity<>(nuevaPublicacionGuardada, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Publicaciones>> obtenerLista() {
        try {
            return new ResponseEntity<>(publicacionesService.findByAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/visibles")
    public List<Publicaciones> getPublicacionesVisibles() {
        return publicacionesRepository.listar();
    }

    @GetMapping("/listaPublicacionesXProductos")
    public List<Publicaciones> getPublicacionesProductos() {
        return publicacionesRepository.listarPublicacionesConProductos();
    }

    @GetMapping("/listaPublicacionesXServicios")
    public List<Publicaciones> getPublicacionesServicios() {
        return publicacionesRepository.listarPublicacionesConServicios();
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
