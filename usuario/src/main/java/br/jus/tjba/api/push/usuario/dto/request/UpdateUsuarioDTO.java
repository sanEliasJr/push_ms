package br.jus.tjba.api.push.usuario.dto.request;

import br.jus.tjba.api.push.usuario.model.EnderecoUsuario;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdateUsuarioDTO(@NotBlank(message = "Favor informar login") String login,
                               @NotBlank(message = "Favor informar nome") String nome,
                               @NotBlank(message = "Favor informar senha") String senha,
                               @NotBlank(message = "Favor informar cpf") String cpf,
                               List<EnderecoUsuario> enderecos) {
}
