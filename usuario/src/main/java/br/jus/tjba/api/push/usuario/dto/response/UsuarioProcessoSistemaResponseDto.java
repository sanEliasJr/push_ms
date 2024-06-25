package br.jus.tjba.api.push.usuario.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record UsuarioProcessoSistemaResponseDto(
    String numeroProcesso,
    String sistema,
    List<UsuarioCustomReponseDTO> usuarios) {
}
