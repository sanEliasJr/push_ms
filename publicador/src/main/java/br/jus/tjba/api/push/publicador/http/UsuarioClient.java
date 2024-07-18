package br.jus.tjba.api.push.publicador.http;

import br.jus.tjba.api.push.publicador.dto.ProcessoSistemaDTO;
import br.jus.tjba.api.push.publicador.dto.ResponseUsuariosDTO;
import br.jus.tjba.api.push.publicador.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("usuario")
public interface UsuarioClient {
    @RequestMapping(method = RequestMethod.POST, value = "/usuarios/buscar-associados-processo")
    ResponseUsuariosDTO obterUsuariosPorSistemaEProcesso(@RequestBody ProcessoSistemaDTO processoSistemaDTO);
}
