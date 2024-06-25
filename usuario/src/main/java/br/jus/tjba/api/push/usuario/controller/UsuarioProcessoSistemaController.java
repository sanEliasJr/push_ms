package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.dto.request.ProcessoSistemaDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioProcessoSistemaResponseDto;
import br.jus.tjba.api.push.usuario.service.UsuarioProcessoSistemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class UsuarioProcessoSistemaController {
    @Autowired
    private UsuarioProcessoSistemaService usuarioProcessoSistemaService;


    @PostMapping("/usuarios/associar-processo")
    public ResponseEntity<String> associarProcesso(@RequestBody @Valid ProcessoSistemaDTO processoSistemaDTO) {
        usuarioProcessoSistemaService.associarProcessoUsuario(processoSistemaDTO);
        return ResponseEntity.ok("Deu certo!");
    }

    @DeleteMapping("/usuarios/desassociar-processo")
    public ResponseEntity<String> desasociarProcesso(@RequestBody @Valid ProcessoSistemaDTO processoSistemaDTO) {
        usuarioProcessoSistemaService.desassociarProcessoUsuario(processoSistemaDTO);
        return ResponseEntity.ok("Deu certo!");
    }

    @PostMapping("/usuarios/buscar-associados-processo")
    public ResponseEntity<UsuarioProcessoSistemaResponseDto> buscarAssociadosProcesso(@RequestBody @Valid ProcessoSistemaDTO processoSistemaDTO) {
        return ResponseEntity.ok(usuarioProcessoSistemaService.buscarUsuarioAssociadoProcesso(processoSistemaDTO));
    }
}
