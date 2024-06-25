package br.jus.tjba.api.push.usuario.pub.service;

import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.security.service.TokenService;
import br.jus.tjba.api.push.usuario.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertTokenService {
    private final UsuarioService usuarioService;
    private final HttpServletRequest httpServletRequest;
    private final TokenService tokenService;

    public Usuario getUsuarioIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            return usuarioService.getUsuarioPorLogin(username);
        }
        return null;
    }
}