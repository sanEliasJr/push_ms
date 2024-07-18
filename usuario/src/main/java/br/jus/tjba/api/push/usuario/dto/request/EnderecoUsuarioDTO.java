package br.jus.tjba.api.push.usuario.dto.request;

import br.jus.tjba.api.push.usuario.enums.EstadosEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoUsuarioDTO(
        @NotBlank(message = "Favor informar rua") String rua,
        @NotBlank(message = "Favor informar bairro") String bairro,
        @NotBlank(message = "Favor informar numero") String numero,
        @NotBlank(message = "Favor informar cidade") String cidade,
        @NotNull(message = "Favor informar UF") EstadosEnum uf,
        @NotBlank(message = "Favor informar cep") String cep) {
}
