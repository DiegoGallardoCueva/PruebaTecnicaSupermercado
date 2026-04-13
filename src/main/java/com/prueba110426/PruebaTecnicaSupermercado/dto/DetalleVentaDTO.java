package com.prueba110426.PruebaTecnicaSupermercado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {
    private Long id;
    private String nombreProducto;
    private Integer cantProducto;
    private Double precio;
    private Double subtotal;    
}
