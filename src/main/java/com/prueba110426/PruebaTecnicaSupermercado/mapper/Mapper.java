package com.prueba110426.PruebaTecnicaSupermercado.mapper;

import java.util.stream.Collectors;

import com.prueba110426.PruebaTecnicaSupermercado.dto.DetalleVentaDTO;
import com.prueba110426.PruebaTecnicaSupermercado.dto.ProductoDTO;
import com.prueba110426.PruebaTecnicaSupermercado.dto.SucursalDTO;
import com.prueba110426.PruebaTecnicaSupermercado.dto.VentaDTO;
import com.prueba110426.PruebaTecnicaSupermercado.model.Producto;
import com.prueba110426.PruebaTecnicaSupermercado.model.Sucursal;
import com.prueba110426.PruebaTecnicaSupermercado.model.Venta;

public class Mapper {
    //Mapeo de producto a productoDTO
    public static ProductoDTO toDTO(Producto producto) {
        if (producto == null) return null;

        return ProductoDTO.builder()
        .id(producto.getId())
        .nombre(producto.getNombre())
        .precio(producto.getPrecio())
        .cantidad(producto.getCantidad())
        .categoria(producto.getCategoria())
        .build();
    }
    //Mapeo de venta a ventaDTO

    public static VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        var detalle = venta.getDetalle().stream().map(det ->
                DetalleVentaDTO.builder()
                        .id(det.getProd().getId())
                        .nombreProducto(det.getProd().getNombre())
                        .cantProducto(det.getCantProd())
                        .precio(det.getPrecio())
                        .subtotal(det.getPrecio() * det.getCantProd())
                        .build()
        ).collect(Collectors.toList());

        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
        .id(venta.getId())
        .fecha(venta.getFecha())
        .idSucursal(venta.getSucursal().getId())
        .estado(venta.getEstado())
        .detalle(detalle)
        .total(total)
        .build();
    }
    //Mapeo de sucursal a sucursalDTO
    public static SucursalDTO toDTO(Sucursal sucursal) {
        if (sucursal == null) return null;

        return SucursalDTO.builder()
        .id(sucursal.getId())
        .nombre(sucursal.getNombre())
        .direccion(sucursal.getDireccion())
        .build();
    }

}
