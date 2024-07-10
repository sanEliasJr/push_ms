package br.jus.tjba.api.push.publicador.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record SinalizarDTO(
        @NotBlank String sigla,
        @NotBlank String numeroProcesso,
        @NotBlank String mensagem) {
}
