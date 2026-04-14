package com.prueba110426.PruebaTecnicaSupermercado.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba110426.PruebaTecnicaSupermercado.dto.DetalleVentaDTO;
import com.prueba110426.PruebaTecnicaSupermercado.dto.VentaDTO;
import com.prueba110426.PruebaTecnicaSupermercado.mapper.Mapper;
import com.prueba110426.PruebaTecnicaSupermercado.model.DetalleVenta;
import com.prueba110426.PruebaTecnicaSupermercado.model.Producto;
import com.prueba110426.PruebaTecnicaSupermercado.model.Sucursal;
import com.prueba110426.PruebaTecnicaSupermercado.model.Venta;
import com.prueba110426.PruebaTecnicaSupermercado.repository.ProductoRepository;
import com.prueba110426.PruebaTecnicaSupermercado.repository.SucursalRepository;
import com.prueba110426.PruebaTecnicaSupermercado.repository.VentaRepository;
import com.prueba110426.exception.NotFoundException;

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
        List<Venta> ventas = VentaRepo.findAll();
        List<VentaDTO> ventaDTOs = new ArrayList<>();
        
        VentaDTO ventaDTO;
        for (Venta venta : ventas) {
            ventaDTO = Mapper.toDTO(venta);
            ventaDTOs.add(ventaDTO);
        }
        return ventaDTOs;

    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        //validaciones
        if (ventaDTO == null) 
            throw new RuntimeException("VentaDTO no puede ser null");
        if (ventaDTO.getIdSucursal() == null) 
            throw new RuntimeException("IdSucursal no puede ser null");
        if (ventaDTO.getDetalle() == null || ventaDTO.getDetalle().isEmpty()) 
            throw new RuntimeException("Detalle de venta no puede ser null o vacío");
        //sucursal
        Sucursal suc = SucursalRepo.findById(ventaDTO.getIdSucursal()).orElse(null);
        if (suc == null) 
            throw new NotFoundException("Sucursal no encontrada para id: " + ventaDTO.getIdSucursal());
        //venta
        Venta vent = new Venta();
        vent.setFecha(ventaDTO.getFecha());
        vent.setEstado(ventaDTO.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDTO.getTotal());

        //Lista de detalles de venta
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDTO detDTO : ventaDTO.getDetalle()) {
            Producto prod = ProductoRepo.findByNombre(detDTO.getNombreProducto()).orElse(null);
            if (prod == null) 
                throw new NotFoundException("Producto no encontrado para nombre: " + detDTO.getNombreProducto());

            //Crear detalle de venta
            DetalleVenta det = new DetalleVenta();
            det.setProd(prod);
            det.setPrecio(detDTO.getPrecio());
            det.setCantProd(detDTO.getCantProducto());
            det.setVenta(vent);
            
            detalles.add(det);
            totalCalculado = totalCalculado + (detDTO.getPrecio() * detDTO.getCantProducto());
        }
        
        //seteamos la lista de detalles venta
        vent.setDetalle(detalles);

        //Guardamos en la DB
        /*  VentaRepo.save(vent)
            VentaDTO ventaSalida = Mapper.toDTO(vent);
            return ventaSalida;*/
        return Mapper.toDTO(VentaRepo.save(vent));
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        //buscar si la venta existe para actualizarla
        Venta v = VentaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");

        if (ventaDTO.getFecha()!=null) {
            v.setFecha(ventaDTO.getFecha());
        }
        if(ventaDTO.getEstado()!=null) {
            v.setEstado(ventaDTO.getEstado());
        }

        if (ventaDTO.getTotal()!=null) {
            v.setTotal(ventaDTO.getTotal());
        }

        if (ventaDTO.getIdSucursal()!=null) {
            Sucursal suc = SucursalRepo.findById(ventaDTO.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        VentaRepo.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = VentaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");
        VentaRepo.delete(v);
    }
    
}
