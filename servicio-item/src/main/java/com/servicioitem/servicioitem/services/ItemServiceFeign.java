package com.servicioitem.servicioitem.services;

import com.servicioitem.servicioitem.clientes.IProductoClienteRest;
import com.servicioitem.servicioitem.models.Item;
import com.servicio.commons.commons.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
public class ItemServiceFeign implements IItemService {

    @Autowired
    private IProductoClienteRest clienteFeign;

    @Override
    public List<Item> findAll() {
        return clienteFeign.listar().stream().map(p -> new Item(p, 1L)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Long cantidad) {
        return new Item(clienteFeign.ver(id), cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        return clienteFeign.crear(producto);
    }

    @Override
    public Producto update(Producto producto, Long id) {
        return clienteFeign.editar(producto, id);
    }

    @Override
    public void delete(Long id) {
        clienteFeign.eliminar(id);
    }
}
