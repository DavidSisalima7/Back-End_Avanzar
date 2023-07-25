package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Productos;
import com.Proyecto.Avanzar.Repository.ProductosRepository;
import com.Proyecto.Avanzar.Services.service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class ProductosServiceImpl extends GenericServiceImpl<Productos, Long> implements ProductosService {
    @Autowired
    private ProductosRepository productosDao;

    @Override
    public CrudRepository<Productos, Long> getDao() {
        return productosDao;
    }
}
