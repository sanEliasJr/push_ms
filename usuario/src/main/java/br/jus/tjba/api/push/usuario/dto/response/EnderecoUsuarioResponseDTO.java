package br.jus.tjba.api.push.usuario.dto.response;

import br.jus.tjba.api.push.usuario.enums.EstadosEnum;
import lombok.Builder;

@Builder
public record EnderecoUsuarioResponseDTO(
        Long idEndereco,
        String rua,
        String bairro,
        String numero,
        String cidade,
        EstadosEnum uf,
        String cep) {
}
