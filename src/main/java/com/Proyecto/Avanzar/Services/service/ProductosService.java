package com.Proyecto.Avanzar.Services.service;

import com.Proyecto.Avanzar.Models.Productos;
import com.Proyecto.Avanzar.Models.Usuario;

import java.util.List;

public interface ProductosService extends GenericService<Productos, Long>{
    public Productos BuscarProductoActivoxId(Long idProducto);
    List<Productos> FiltradoProdxEstadoActivo();
    List<Productos> FiltradoProdxEstadoInactivo();
}