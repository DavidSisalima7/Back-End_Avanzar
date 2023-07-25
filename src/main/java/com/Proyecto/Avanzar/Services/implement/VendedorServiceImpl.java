package com.Proyecto.Avanzar.Services.implement;

import com.Proyecto.Avanzar.Models.Vendedor;
import com.Proyecto.Avanzar.Repository.VendedorRepository;
import com.Proyecto.Avanzar.Services.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class VendedorServiceImpl extends GenericServiceImpl<Vendedor, Long> implements VendedorService {
    @Autowired
    private VendedorRepository vendedorDao;

    @Override
    public CrudRepository<Vendedor, Long> getDao() {
        return vendedorDao;
    }
}
