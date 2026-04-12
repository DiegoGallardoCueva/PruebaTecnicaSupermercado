package com.prueba110426.PruebaTecnicaSupermercado.controller;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Venta
    @ManyToOne
    private Venta venta;

    //Producto
    @ManyToOne
    private Producto prod;
    private Integer cantProd;
    private Double precio;
}
