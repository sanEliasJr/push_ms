package br.jus.tjba.api.push.usuario.dto.request;

import jakarta.validation.constraints.NotNull;

public record ProcessoSistemaDTO(
        @NotNull(message = "Favor informar o numero do processo") String numeroProcesso,
        @NotNull(message = "Favor informar o sistema") String sistema) {
}
