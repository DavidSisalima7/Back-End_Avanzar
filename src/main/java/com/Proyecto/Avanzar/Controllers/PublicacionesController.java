package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Models.Categoria;
import com.Proyecto.Avanzar.Models.Productos;
import com.Proyecto.Avanzar.Models.Publicaciones;
import com.Proyecto.Avanzar.Services.service.CategoriaService;
import com.Proyecto.Avanzar.Services.service.ProductosService;
import com.Proyecto.Avanzar.Services.service.PublicacionesService;
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
@RequestMapping("/api/publicaciones")
public class PublicacionesController {
    @Autowired
    PublicacionesService publicacionesService;
    @Autowired
    ProductosService productosService;
    @Autowired
    CategoriaService categoriaService;
    @PostMapping("/registrar")
    public ResponseEntity<Publicaciones> crear(@RequestBody Publicaciones request) {
        try {
            // Crear una nueva instancia de Publicaciones a partir de la solicitud
            Publicaciones nuevaPublicacion = new Publicaciones();
            nuevaPublicacion.setEstado(true);
            // Obtener el producto desde el servicio de productos (supongamos que tienes un servicio llamado productosService)
            Productos producto = new Productos();
            producto.setEstadoProducto(true);

            Productos nuevoProducto = productosService.save(producto);

            Categoria categoria = categoriaService.findById(1L);
            nuevaPublicacion.setCategoria(categoria);
            // Asignar el producto a la nueva publicación
            nuevaPublicacion.setProductos(nuevoProducto);

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
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @RequestBody Publicaciones publicaciones) {
        return publicacionesService.delete(id);
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
                publicaciones.setFechaPublicacion(p.getFechaPublicacion());
                publicaciones.setEstado(p.isEstado());
                publicaciones.setListalikes(p.getListalikes());
                publicaciones.setListacomentarios(p.getListacomentarios());
                publicaciones.setVendedor(p.getVendedor());
                publicaciones.setCategoria(p.getCategoria());
                publicaciones.setProductos(p.getProductos());
                publicaciones.setServicios(p.getServicios());
                
                return new ResponseEntity<>(publicacionesService.save(publicaciones), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }
}
