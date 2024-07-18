package br.jus.tjba.api.push.usuario.dto.request;

import br.jus.tjba.api.push.usuario.model.EnderecoUsuario;

import java.util.List;

public record UpdateUsuarioDTO(String login,
                               String nome,
                               String senha,
                               String cpf,
                               List<EnderecoUsuario> enderecos) {
}
