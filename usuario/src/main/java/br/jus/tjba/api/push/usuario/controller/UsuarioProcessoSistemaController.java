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
@RequestMapping
public class UsuarioProcessoSistemaController {
    @Autowired
    private UsuarioProcessoSistemaService usuarioProcessoSistemaService;


    @PostMapping("/usuarios/associar-processo")
    public ResponseEntity<?> associarProcesso(@RequestBody @Valid ProcessoSistemaDTO processoSistemaDTO) {
        return usuarioProcessoSistemaService.associarProcessoUsuario(processoSistemaDTO);
    }

    @DeleteMapping("/usuarios/desassociar-processo")
    public ResponseEntity<?> desasociarProcesso(@RequestBody @Valid ProcessoSistemaDTO processoSistemaDTO) {
        usuarioProcessoSistemaService.desassociarProcessoUsuario(processoSistemaDTO);
        return ResponseEntity.ok("Deu certo!");
    }

    @PostMapping("/usuarios/buscar-associados-processo")
    public ResponseEntity<?> buscarAssociadosProcesso(@RequestBody @Valid ProcessoSistemaDTO processoSistemaDTO) {
        return ResponseEntity.ok(usuarioProcessoSistemaService.buscarUsuarioAssociadoProcesso(processoSistemaDTO));
    }
}
