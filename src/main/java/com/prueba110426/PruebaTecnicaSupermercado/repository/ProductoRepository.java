package com.prueba110426.PruebaTecnicaSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba110426.PruebaTecnicaSupermercado.model.Producto;

public interface ProductoRepository extends JpaRepository <Producto, Long>{
    
}
