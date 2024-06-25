package br.jus.tjba.api.push.usuario.dto.response;

import lombok.Builder;

@Builder
public record UsuarioCustomReponseDTO(
        Long id,
        String login
) {
}
