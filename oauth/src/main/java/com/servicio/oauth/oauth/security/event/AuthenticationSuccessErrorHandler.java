package com.servicio.oauth.oauth.security.event;

import com.servicio.oauth.oauth.services.IUsuarioService;
import com.usuario.commons.usuariocommons.models.entity.Usuario;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        String username = authentication.getName();

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String message = "Success login: ".concat(username);
        System.out.println(message);
        log.info(message);

        Usuario usuario = usuarioService.findByUsername(username);

        if (usuario.getIntentos() != 0){
            usuario.setIntentos(0);
            usuarioService.update(usuario, usuario.getId());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException e, Authentication authentication) {
        String username = authentication.getName();

        String message = "Error login: ".concat(e.getMessage());
        System.out.println(message);
        log.info(message);

        try {

            Usuario usuario = usuarioService.findByUsername(username);

            if (usuario.getIntentos() < 3) {
                usuario.setIntentos(usuario.getIntentos() + 1);
                log.info("Intentos restantes: " + (3 - usuario.getIntentos()));
            } else {
                log.error(String.format("Usuario %s deshabilitado por maximo de intentos", username));
                usuario.setEnabled(false);
            }
            usuarioService.update(usuario, usuario.getId());

        } catch (FeignException fe) {
            log.error(String.format("El usuario %s no existe", username));
        }

    }
}
