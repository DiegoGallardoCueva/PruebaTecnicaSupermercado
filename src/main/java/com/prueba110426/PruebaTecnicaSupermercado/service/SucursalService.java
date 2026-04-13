package com.prueba110426.PruebaTecnicaSupermercado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba110426.PruebaTecnicaSupermercado.dto.SucursalDTO;
import com.prueba110426.PruebaTecnicaSupermercado.mapper.Mapper;
import com.prueba110426.PruebaTecnicaSupermercado.model.Sucursal;
import com.prueba110426.PruebaTecnicaSupermercado.repository.SucursalRepository;
import com.prueba110426.exception.NotFoundException;

@Service
public class SucursalService implements ISucursalService{

    @Autowired
    private SucursalRepository repo;

    @Override
    public List<SucursalDTO> traerSucursales() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDTO) {
        Sucursal suc = Sucursal.builder()
        .nombre(sucursalDTO.getNombre())
        .direccion(sucursalDTO.getDireccion())
        .build();
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDTO) {
        Sucursal suc = repo.findById(id).orElseThrow(() -> 
        new NotFoundException("Sucursal no encontrada"));
        suc.setNombre(sucursalDTO.getNombre());
        suc.setDireccion(sucursalDTO.getDireccion());
        return Mapper.toDTO(repo.save(suc));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Sucursal no encontrada para eliminar");
        }
        repo.deleteById(id);
    }
    
}
