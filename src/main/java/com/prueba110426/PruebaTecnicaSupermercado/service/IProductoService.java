package com.prueba110426.PruebaTecnicaSupermercado.service;

import java.util.List;

import com.prueba110426.PruebaTecnicaSupermercado.dto.ProductoDTO;

public interface IProductoService {
    
    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}
