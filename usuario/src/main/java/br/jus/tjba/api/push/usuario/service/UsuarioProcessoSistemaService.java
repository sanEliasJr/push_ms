package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.UsuarioDTO;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioProcessoSistemaService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    public Usuario getEnderecoUsuarioById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveEnderecoUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = Usuario.builder().login(usuarioDTO.login())
                .senha(usuarioDTO.senha())
                .cpf(usuarioDTO.cpf())
                .build();

        return usuarioRepository.save(usuario);
    }

    public void deleteEnderecoUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
