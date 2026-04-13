package com.prueba110426.PruebaTecnicaSupermercado.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double total;
    //datos de la sucursal
    private Long idSucursal;
    //Lista de detalles
    private List<DetalleVentaDTO> detalle;
}
