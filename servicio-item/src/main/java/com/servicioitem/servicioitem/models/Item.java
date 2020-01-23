package com.servicioitem.servicioitem.models;

import com.servicio.commons.commons.models.entity.Producto;

public class Item {

    private Producto producto;
    private Long cantidad;

    public Item(Producto producto, Long cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Item () {
        //
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return producto.getPrecio() * this.cantidad;
    }
}
