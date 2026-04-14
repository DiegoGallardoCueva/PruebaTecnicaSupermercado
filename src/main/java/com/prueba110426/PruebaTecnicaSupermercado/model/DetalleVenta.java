package com.prueba110426.PruebaTecnicaSupermercado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Venta
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ventaId")
    private Venta venta;

    //Producto
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "productoId")
    private Producto prod;
    
    private Integer cantProd;
    private Double precio;
}
