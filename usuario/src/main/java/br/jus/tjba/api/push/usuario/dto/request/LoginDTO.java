package br.jus.tjba.api.push.usuario.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String login,
        @NotBlank String senha)  {
}
