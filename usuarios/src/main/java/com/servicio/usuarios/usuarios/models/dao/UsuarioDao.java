package com.servicio.usuarios.usuarios.models.dao;

import com.usuario.commons.usuariocommons.models.entity.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

    @RestResource(path = "buscar-username")
    Usuario findByUsername(@Param("username") String username);

}
