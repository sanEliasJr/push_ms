package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.LoginDTO;
import br.jus.tjba.api.push.usuario.dto.UsuarioDTO;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import br.jus.tjba.api.push.usuario.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;



    public Usuario getUsuarioById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = Usuario.builder().login(usuarioDTO.login())
                                .senha(usuarioDTO.senha())
                                .cpf(usuarioDTO.cpf())
                                .build();

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario updateUsuario(Long id, UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        usuario.setCpf(usuarioDTO.cpf());
        usuario.setLogin(usuarioDTO.login());
        usuario.setSenha(usuarioDTO.senha());
        return usuarioRepository.save(usuario);
    };

    public ResponseEntity<String> login(LoginDTO loginDTO) {

        Usuario usuario = usuarioRepository.findUsuarioByLogin(loginDTO.login());

        var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.getId(), usuario.getSenha());

        Authentication authentication = this.authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.accepted().body(token);
    }
}
