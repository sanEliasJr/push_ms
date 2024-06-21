package br.jus.tjba.api.push.usuario.security.configuration;

import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import br.jus.tjba.api.push.usuario.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;
    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        if(shouldSkipFilter(request)){
            filterChain.doFilter(request, response);
            return;
        }

        String token = this.tokenService.recoverToken(request);

        try {
            if (token != null) {
                Long id = tokenService.validateToken(token);
                UserDetails user = usuarioRepository.findUsuarioById(id);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        filterChain.doFilter(request, response);
    }

    private boolean shouldSkipFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String method = request.getMethod();

        return uri.startsWith("/swagger-ui/") || method.equalsIgnoreCase("OPTIONS");
    }
}
