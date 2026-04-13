package com.prueba110426.PruebaTecnicaSupermercado.service;

import java.util.List;

import com.prueba110426.PruebaTecnicaSupermercado.dto.SucursalDTO;

public interface ISucursalService {
    List<SucursalDTO> traerSucursales();
    SucursalDTO crearSucursal(SucursalDTO sucursalDTO);
    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO);
    void eliminarSucursal(Long id);
}
