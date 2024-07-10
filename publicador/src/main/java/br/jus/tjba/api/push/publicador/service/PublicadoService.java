package br.jus.tjba.api.push.publicador.service;

import br.jus.tjba.api.push.publicador.dto.ProcessoSistemaDTO;
import br.jus.tjba.api.push.publicador.dto.SinalizarDTO;
import br.jus.tjba.api.push.publicador.dto.UsuarioDTO;
import br.jus.tjba.api.push.publicador.http.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PublicadorService {

    @Autowired
    private UsuarioClient usuarioClient;

    public ResponseEntity<String> sinalizar(SinalizarDTO sinalizarDTO){
        List<UsuarioDTO> usuarios =  usuarioClient.obterUsuariosPorSistemaEProcesso(ProcessoSistemaDTO.builder()
                        .numeroProcesso(sinalizarDTO.numeroProcesso())
                        .sistema(sinalizarDTO.sigla())
                        .build());
    }
}
