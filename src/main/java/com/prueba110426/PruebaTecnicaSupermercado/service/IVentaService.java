package com.prueba110426.PruebaTecnicaSupermercado.service;

import java.util.List;

import com.prueba110426.PruebaTecnicaSupermercado.dto.VentaDTO;

public interface IVentaService {
    List<VentaDTO> traerVentas();
    VentaDTO crearVenta(VentaDTO ventaDTO);
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO);
    void eliminarVenta(Long id);
}
