package com.servicioitem.servicioitem.clientes;

import com.servicio.commons.commons.models.entity.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient( name = "servicio-productos")
public interface IProductoClienteRest {

    @GetMapping("/listar")
    List<Producto> listar();

    @GetMapping("/ver/{id}")
    Producto ver(@PathVariable Long id);

    @PostMapping("/crear")
    Producto crear(@RequestBody Producto producto);

    @PutMapping("/editar/{id}")
    Producto editar(@RequestBody Producto producto, @PathVariable Long id);

    @DeleteMapping("/borrar/{id}")
    void eliminar(@PathVariable Long id);

}
