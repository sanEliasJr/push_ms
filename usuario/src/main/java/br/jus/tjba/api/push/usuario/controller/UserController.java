package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.dto.LoginDTO;
import br.jus.tjba.api.push.usuario.dto.UsuarioDTO;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Usuario> getUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping("/usuarios")
    public Usuario saveUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        return usuarioService.saveUsuario(usuarioDTO);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
        return usuarioService.updateUsuario(id, usuarioDTO);
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
