package br.jus.tjba.api.push.usuario.controller;

import br.jus.tjba.api.push.usuario.dto.request.*;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioReponseDTO;
import br.jus.tjba.api.push.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping()
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        return usuarioService.login(loginDTO);
    }
    @GetMapping("/usuarios")
    public Page<UsuarioReponseDTO> getUsuarios(Pageable pageable) {
        return usuarioService.getAllUsuarios(pageable);
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

}