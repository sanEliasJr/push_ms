package br.jus.tjba.api.push.usuario.dto;

import br.jus.tjba.api.push.usuario.enums.EstadosEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoUsuarioDTO(
        @NotBlank String rua,
        @NotBlank String bairro,
        @NotBlank String numero,
        @NotBlank String cidade,
        @NotNull EstadosEnum uf,
        @NotBlank String cep,
        @NotBlank Long idUsuario) {
}
