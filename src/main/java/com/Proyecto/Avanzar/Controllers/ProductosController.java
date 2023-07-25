package com.Proyecto.Avanzar.Controllers;

import com.Proyecto.Avanzar.Services.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api/productos")
public class ProductosController {
    @Autowired
    ProductosService productosService;
}
