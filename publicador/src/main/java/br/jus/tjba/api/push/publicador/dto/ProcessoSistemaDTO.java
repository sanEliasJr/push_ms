package br.jus.tjba.api.push.publicador.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProcessoSistemaDTO(
        @NotNull(message = "Favor informar o numero do processo") String numeroProcesso,
        @NotNull(message = "Favor informar o sistema") String sistema) {
}
