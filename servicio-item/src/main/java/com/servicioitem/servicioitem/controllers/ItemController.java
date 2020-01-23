package com.servicioitem.servicioitem.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Http;
import com.servicioitem.servicioitem.models.Item;
import com.servicio.commons.commons.models.entity.Producto;
import com.servicioitem.servicioitem.services.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//refreshScope nos permite actualizar los componentes, refrescando el contexto e inicializando el componente,
// sin reiniciar el servidor mediante un endpoint (spring actuator)
@RefreshScope
@RestController
public class ItemController {

    private static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("serviceFeign")
    private IItemService itemService;

    @Value("${texto}")
    private String texto;

    @RequestMapping("/listar")
    public List<Item> listar(){
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @RequestMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Long cantidad){
        return itemService.findById(id, cantidad);
    }

    public Item metodoAlternativo(Long id, Long cantidad){
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500.00);
        item.setProducto(producto);

        return item;
    }

    @RequestMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfiguracion(@Value("${server.port}") String puerto) {
        logger.info(texto);
        Map<String,String> json = new HashMap<>();
        json.put("texto", texto);
        json.put("puerto", puerto);

        if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
            json.put("autor.nombre", env.getProperty("autor.nombre"));
            json.put("autor.email", env.getProperty("autor.email"));
        }

        return new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@RequestBody Producto producto){
        return itemService.save(producto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto editar(@RequestBody Producto producto, @PathVariable Long id){
        return itemService.update(producto, id);
    }

    @DeleteMapping("/borrar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable  Long id){
        itemService.delete(id);
    }

}
