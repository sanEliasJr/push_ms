package br.jus.tjba.api.push.usuario.dto.local;

import br.jus.tjba.api.push.usuario.dto.request.EnderecoUsuarioDTO;
import br.jus.tjba.api.push.usuario.model.Usuario;

import java.util.List;

public record PersisteEnderecoUsuarioDTO(
        List<EnderecoUsuarioDTO> enderecoUsuarioDTOList,
        Usuario usuario) {
}
