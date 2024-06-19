package br.jus.tjba.api.push.usuario.dto.response;

import br.jus.tjba.api.push.usuario.dto.request.EnderecoUsuarioDTO;
import br.jus.tjba.api.push.usuario.model.EnderecoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@Builder
public record UsuarioReponseDTO (
        Long idUsuario,
        String login,
        String senha,
        String cpf,
        List<EnderecoUsuarioResponseDTO> enderecos) {


}
