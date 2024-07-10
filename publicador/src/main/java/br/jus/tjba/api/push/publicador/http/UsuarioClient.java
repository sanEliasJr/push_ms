package br.jus.tjba.api.push.publicador.http;

import br.jus.tjba.api.push.publicador.dto.ProcessoSistemaDTO;
import br.jus.tjba.api.push.publicador.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("usuario")
public interface UsuarioClient {
    @RequestMapping(method = RequestMethod.POST, value = "/usuarios/buscar-associados-processo")
    List<UsuarioDTO> obterUsuariosPorSistemaEProcesso(@RequestBody ProcessoSistemaDTO processoSistemaDTO);
}
