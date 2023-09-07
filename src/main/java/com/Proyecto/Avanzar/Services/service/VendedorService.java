package com.Proyecto.Avanzar.Services.service;

import com.Proyecto.Avanzar.Models.Vendedor;

import java.util.List;

public interface VendedorService extends GenericService<Vendedor, Long>{
    List<Vendedor> getVendedoresByUsuarioId(Long userId);
}
