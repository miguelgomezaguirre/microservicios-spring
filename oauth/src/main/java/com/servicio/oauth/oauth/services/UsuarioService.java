package com.servicio.oauth.oauth.services;

import com.servicio.oauth.oauth.clients.UsuarioFeignClient;
import com.usuario.commons.usuariocommons.models.entity.Usuario;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    private Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioFeignClient client;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario usuario = client.findByUsername(username);

            List<GrantedAuthority> authorities = usuario.getRoles()
                    .stream().map(
                            role -> new SimpleGrantedAuthority(role.getNombre())
                    )
                    .peek(
                            authoritie -> log.info(authoritie.getAuthority())
                    )
                    .collect(Collectors.toList());

            log.info("Usuario autenticado " + username);

            return new User(username, usuario.getPassword(), usuario.getEnabled(),
                    true, true, true,
                    authorities);

        } catch (FeignException e) {
            log.error("No existe el usuario " + username);
            throw new UsernameNotFoundException("No existe el usuario " + username);
        }
    }

    @Override
    public Usuario findByUsername(String username) {
        return client.findByUsername(username);
    }

    @Override
    public Usuario update(Usuario usuario, Long id) {
        return client.update(usuario, id);
    }
}
