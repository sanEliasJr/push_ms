package br.jus.tjba.api.push.usuario.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(@NotBlank(message = "Favor informar o login") String login,
                         @NotBlank(message = "Favor informar o nome") String nome,
                         @NotBlank(message = "Favor informar a senha") String senha,
                         @NotBlank(message = "Favor informar o cpf") String cpf,
                         List<EnderecoUsuarioDTO> enderecos) {

}
