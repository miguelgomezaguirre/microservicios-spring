package com.servicioproductos.servicioproductos.controllers;

import com.netflix.ribbon.proxy.annotation.Http;
import com.servicio.commons.commons.models.entity.Producto;
import com.servicioproductos.servicioproductos.models.services.IProductoService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController("producto")
public class ProductoController {

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IProductoService productoService;

    @RequestMapping("/listar")
    public List<Producto> listar() {
        return productoService.findAll().stream().map(p -> {
            p.setPort(port);
            return p;
        }).collect(Collectors.toList());
    }

    @RequestMapping("/ver/{id}")
    public Producto ver(@PathVariable Long id) throws Exception{
        Producto producto = productoService.findById(id);
        producto.setPort(port);

//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return producto;
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto){
        return productoService.save(producto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto editar(@RequestBody Producto producto, @PathVariable Long id){
        Producto productoEditar = productoService.findById(id);
        productoEditar.setNombre(producto.getNombre());
        productoEditar.setPrecio(producto.getPrecio());
        return productoService.save(productoEditar);
    }

    @DeleteMapping("/borrar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id){
        productoService.deleteById(id);
    }


}
