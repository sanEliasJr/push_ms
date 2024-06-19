package br.jus.tjba.api.push.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record ProcessoDTO(
        @NotBlank String processo) {
}
