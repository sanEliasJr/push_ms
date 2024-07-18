package br.jus.tjba.api.push.notificador.dto;

import java.io.Serializable;

public record MensagemUsuarioDTO(
        String numeroProcesso,
        String sistema,
        String email,
        String nome,
        String mensagem) implements Serializable{
}
