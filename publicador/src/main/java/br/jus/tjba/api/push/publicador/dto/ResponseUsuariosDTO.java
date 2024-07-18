package br.jus.tjba.api.push.publicador.dto;

import java.util.List;

public record ResponseUsuariosDTO(
        String numeroProcesso,
        String sistema,
        List<UsuarioDTO> usuarios) {
}
