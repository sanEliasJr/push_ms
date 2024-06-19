package br.jus.tjba.api.push.usuario.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProcessoDTO(
        @NotBlank String processo) {
}
