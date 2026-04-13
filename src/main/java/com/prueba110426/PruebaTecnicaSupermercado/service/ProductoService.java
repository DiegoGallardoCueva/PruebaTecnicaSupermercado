package com.prueba110426.PruebaTecnicaSupermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba110426.PruebaTecnicaSupermercado.dto.ProductoDTO;
import com.prueba110426.PruebaTecnicaSupermercado.mapper.Mapper;
import com.prueba110426.PruebaTecnicaSupermercado.model.Producto;
import com.prueba110426.PruebaTecnicaSupermercado.repository.ProductoRepository;
import com.prueba110426.exception.NotFoundException;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> traerProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto prod = Producto.builder()
        .nombre(productoDTO.getNombre())
        .categoria(productoDTO.getCategoria())
        .precio(productoDTO.getPrecio())
        .cantidad(productoDTO.getCantidad())
        .build();
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto prod = repo.findById(id).orElseThrow(() -> 
        new NotFoundException("Producto no encontrado"));
        prod.setNombre(productoDTO.getNombre());
        prod.setCategoria(productoDTO.getCategoria());
        prod.setPrecio(productoDTO.getPrecio());
        prod.setCantidad(productoDTO.getCantidad());
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Producto no encontrado para eliminar");
        } else {
            repo.deleteById(id);
        }
    }
    
}
