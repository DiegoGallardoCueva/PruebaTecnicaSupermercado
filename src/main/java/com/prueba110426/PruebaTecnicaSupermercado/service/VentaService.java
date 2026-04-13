package com.prueba110426.PruebaTecnicaSupermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba110426.PruebaTecnicaSupermercado.dto.VentaDTO;
import com.prueba110426.PruebaTecnicaSupermercado.repository.ProductoRepository;
import com.prueba110426.PruebaTecnicaSupermercado.repository.SucursalRepository;
import com.prueba110426.PruebaTecnicaSupermercado.repository.VentaRepository;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private VentaRepository VentaRepo;
    @Autowired
    private ProductoRepository ProductoRepo;
    @Autowired
    private SucursalRepository SucursalRepo;

    @Override
    public List<VentaDTO> traerVentas() {
        return List.of();        
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearVenta'");
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarVenta'");
    }

    @Override
    public void eliminarVenta(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarVenta'");
    }
    
}
