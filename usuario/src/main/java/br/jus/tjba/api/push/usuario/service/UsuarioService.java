package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.request.*;
import br.jus.tjba.api.push.usuario.dto.local.PersisteEnderecoUsuarioDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioCustomReponseDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioReponseDTO;
import br.jus.tjba.api.push.usuario.model.Sistema;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import br.jus.tjba.api.push.usuario.pub.service.ConvertTokenService;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import br.jus.tjba.api.push.usuario.security.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EnderecoUsuarioService enderecoUsuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;



    public UsuarioReponseDTO getUsuarioById(Long id){
        if (usuarioRepository.existsById(id)) {
            return converterUsuarioemDtoRespose(usuarioRepository.findById(id).get());
        }
        return null;
    }

    public Usuario getUsuarioByID(Long id){
        if (usuarioRepository.existsById(id)) {
            return usuarioRepository.findById(id).orElse(null);
        }
        return null;
    }

    @Transactional
    public UsuarioReponseDTO saveUsuario(UsuarioDTO usuarioDTO){
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());
        Usuario usuario = Usuario.builder().login(usuarioDTO.login())
                                .senha(senhaCriptografada)
                                .cpf(usuarioDTO.cpf())
                                .build();
        PersisteEnderecoUsuarioDTO persisteEnderecoUsuarioDTO = new PersisteEnderecoUsuarioDTO(usuarioDTO.enderecos(),usuario);
        usuario.setEnderecos(enderecoUsuarioService.criaListaEnderecoUsuario(persisteEnderecoUsuarioDTO));
        return converterUsuarioemDtoRespose(usuarioRepository.save(usuario));
    }

    public void deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

    public Page<UsuarioReponseDTO> getAllUsuarios(Pageable pageable){
        return usuarioRepository.findAll(pageable).map(this::converterUsuarioemDtoRespose);
    }

    public UsuarioReponseDTO updateUsuario(Long id, UpdateUsuarioDTO updateUsuarioDTO){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        usuario.setCpf(updateUsuarioDTO.cpf().isBlank() ? usuario.getCpf() : updateUsuarioDTO.cpf());
        usuario.setLogin(updateUsuarioDTO.login().isBlank() ? usuario.getLogin() : updateUsuarioDTO.login());
        usuario.setSenha(updateUsuarioDTO.senha().isBlank() ? usuario.getSenha() : updateUsuarioDTO.senha());
        usuario.setEnderecos(updateUsuarioDTO.enderecos().isEmpty() ? usuario.getEnderecos() : updateUsuarioDTO.enderecos());
        return converterUsuarioemDtoRespose(usuarioRepository.save(usuario));
    };

    public ResponseEntity<String> login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findUsuarioByLogin(loginDTO.login());
        var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.getId(), loginDTO.senha());
        Authentication authentication = this.authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.accepted().body(token);
    }


    private UsuarioReponseDTO converterUsuarioemDtoRespose(Usuario usuario){
        UsuarioReponseDTO usuarioReponseDTO = UsuarioReponseDTO.builder()
                .idUsuario(usuario.getId())
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .cpf(usuario.getCpf())
                .enderecos(usuario.getEnderecos()
                        .stream()
                        .map(endereco -> enderecoUsuarioService.converterEnderecoUsuarioemDtoRespose(endereco))
                        .collect(Collectors.toList())
                )
                .build();
        return usuarioReponseDTO;
    }

    public Usuario getUsuarioPorLogin(String login){
        Usuario usuario = usuarioRepository.findUsuarioByLogin(login);
        return usuario;
    }

    public UsuarioCustomReponseDTO converterUsuarioemDtoResponse(Usuario usuario){
        UsuarioCustomReponseDTO usuarioCustomReponseDTO = UsuarioCustomReponseDTO.builder()
                .id(usuario.getId())
                .login(usuario.getLogin())
                .build();
        return usuarioCustomReponseDTO;
    }

}
