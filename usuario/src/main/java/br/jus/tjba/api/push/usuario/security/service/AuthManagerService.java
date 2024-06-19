package br.jus.tjba.api.push.usuario.security.service;


import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthManagerService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AuthManagerService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return usuarioRepository.findUsuarioById(Long.valueOf(id));
    }
}
