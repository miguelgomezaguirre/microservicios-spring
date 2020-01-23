package com.servicioitem.servicioitem.services;

import com.servicioitem.servicioitem.models.Item;
import com.servicio.commons.commons.models.entity.Producto;

import java.util.List;

public interface IItemService {

    List<Item> findAll();
    Item findById(Long id, Long cantidad);
    Producto save(Producto producto);
    Producto update(Producto producto, Long id);
    void delete(Long id);
}
