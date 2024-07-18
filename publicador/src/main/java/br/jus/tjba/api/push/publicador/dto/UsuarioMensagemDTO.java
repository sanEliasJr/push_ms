package br.jus.tjba.api.push.publicador.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record UsuarioMensagemDTO(
        String numeroProcesso,
        String sistema,
        String email,
        String nome,
        String mensagem) {
}
