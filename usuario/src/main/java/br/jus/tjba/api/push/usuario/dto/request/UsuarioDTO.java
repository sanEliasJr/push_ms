package br.jus.tjba.api.push.usuario.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(@NotBlank String login,
                         @NotBlank String senha,
                         @NotBlank String cpf,
                         List<EnderecoUsuarioDTO> enderecos) {

}
