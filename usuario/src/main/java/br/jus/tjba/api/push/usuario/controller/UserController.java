package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.dto.request.LoginDTO;
import br.jus.tjba.api.push.usuario.dto.request.UpdateUsuarioDTO;
import br.jus.tjba.api.push.usuario.dto.request.UsuarioDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioReponseDTO;
import br.jus.tjba.api.push.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        return usuarioService.login(loginDTO);
    }
    @GetMapping("/usuarios")
    public List<UsuarioReponseDTO> getUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioReponseDTO getUsuario(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping("/usuarios")
    public UsuarioReponseDTO saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return usuarioService.saveUsuario(usuarioDTO);
    }

    @PutMapping("/usuarios/{id}")
    public UsuarioReponseDTO updateUsuario(@PathVariable Long id, @RequestBody @Valid UpdateUsuarioDTO updateUsuarioDTO) {
        return usuarioService.updateUsuario(id, updateUsuarioDTO);
    }

    @DeleteMapping("/usuarios/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    @PostMapping("/usuarios/associar-processo")
    public ResponseEntity<String> associarProcesso(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok("Deu certo!");

    }
}
