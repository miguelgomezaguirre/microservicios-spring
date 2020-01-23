package com.servicioproductos.servicioproductos.models.dao;

import com.servicio.commons.commons.models.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}
