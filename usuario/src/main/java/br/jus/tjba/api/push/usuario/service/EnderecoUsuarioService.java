package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.EnderecoUsuarioDTO;
import br.jus.tjba.api.push.usuario.model.EnderecoUsuario;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.repository.EnderecoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoUsuarioService {
    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public EnderecoUsuario getUsuarioById(Long id){
        return enderecoUsuarioRepository.findById(id).orElse(null);
    }

    public EnderecoUsuario saveUsuario(EnderecoUsuarioDTO enderecoUsuarioDTO){
        EnderecoUsuario enderecoUsuario = EnderecoUsuario.builder()
                .rua(enderecoUsuarioDTO.rua())
                .bairro(enderecoUsuarioDTO.bairro())
                .cidade(enderecoUsuarioDTO.cidade())
                .cep(enderecoUsuarioDTO.cep())
                .uf(enderecoUsuarioDTO.uf())
                .numero(enderecoUsuarioDTO.numero())
                .usuario(usuarioService.getUsuarioById(enderecoUsuarioDTO.idUsuario()))
                .build();

        return enderecoUsuarioRepository.save(enderecoUsuario);
    }

    public void deleteUsuario(Long id){
        enderecoUsuarioRepository.deleteById(id);
    }
}
