package com.servicio.oauth.oauth.services;

import com.usuario.commons.usuariocommons.models.entity.Usuario;

public interface IUsuarioService {

    Usuario findByUsername(String username);

    Usuario update(Usuario usuario, Long id);

}
