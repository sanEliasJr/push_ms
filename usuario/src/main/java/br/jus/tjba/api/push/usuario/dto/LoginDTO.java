package br.jus.tjba.api.push.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank String login,
        @NotBlank String senha)  {
}
