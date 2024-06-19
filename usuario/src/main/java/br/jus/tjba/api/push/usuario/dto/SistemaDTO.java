package br.jus.tjba.api.push.usuario.dto;

import br.jus.tjba.api.push.usuario.enums.SiglaEnum;
import jakarta.validation.constraints.NotNull;

public record SistemaDTO(
        @NotNull SiglaEnum sigla) {
}
