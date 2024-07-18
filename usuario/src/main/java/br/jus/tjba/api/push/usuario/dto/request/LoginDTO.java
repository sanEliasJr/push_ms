package br.jus.tjba.api.push.usuario.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Favor informar o login") String login,
        @NotBlank(message = "Favor informar a senha") String senha)  {
}
