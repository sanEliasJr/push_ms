package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.request.*;
import br.jus.tjba.api.push.usuario.dto.local.PersisteEnderecoUsuarioDTO;
import br.jus.tjba.api.push.usuario.dto.response.AcessoResponseDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioCustomReponseDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioReponseDTO;
import br.jus.tjba.api.push.usuario.model.Sistema;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import br.jus.tjba.api.push.usuario.pub.exceptions.NegocioException;
import br.jus.tjba.api.push.usuario.pub.service.ConvertTokenService;
import br.jus.tjba.api.push.usuario.pub.service.ResponseService;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import br.jus.tjba.api.push.usuario.security.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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



    public ResponseEntity<Object> getUsuarioById(Long id){
        if (usuarioRepository.existsById(id)) {
            return ResponseEntity.ok().body(converterUsuarioemDtoRespose(usuarioRepository.findById(id).get()));
        }
        throw  new NegocioException("Usuario nao encontrado!");
    }

    @Transactional
    public ResponseEntity<Object> saveUsuario(UsuarioDTO usuarioDTO){
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());
        Usuario usuario = Usuario.builder().login(usuarioDTO.login())
                                .nome(usuarioDTO.nome())
                                .senha(senhaCriptografada)
                                .cpf(usuarioDTO.cpf())
                                .build();
        PersisteEnderecoUsuarioDTO persisteEnderecoUsuarioDTO = new PersisteEnderecoUsuarioDTO(usuarioDTO.enderecos(),usuario);
        usuario.setEnderecos(enderecoUsuarioService.criaListaEnderecoUsuario(persisteEnderecoUsuarioDTO));
        return ResponseEntity.accepted().body(converterUsuarioemDtoRespose(usuarioRepository.save(usuario)));
    }

    public ResponseEntity<Object> deleteUsuario(Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<UsuarioReponseDTO>> getAllUsuarios(Pageable pageable){
        return ResponseEntity.ok().body(usuarioRepository.findAll(pageable).map(this::converterUsuarioemDtoRespose));
    }
    @Transactional
    public ResponseEntity<Object> updateUsuario(Long id, UpdateUsuarioDTO updateUsuarioDTO){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new NegocioException("Usuario não encontrado!");
        }
        usuario.setCpf(updateUsuarioDTO.cpf());
        usuario.setNome(updateUsuarioDTO.nome());
        usuario.setLogin(updateUsuarioDTO.login());
        usuario.setSenha(updateUsuarioDTO.senha());
        usuario.setEnderecos(updateUsuarioDTO.enderecos());
        return ResponseEntity.accepted().body(converterUsuarioemDtoRespose(usuarioRepository.save(usuario)));
    };

    public ResponseEntity<Object> login(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findUsuarioByLogin(loginDTO.login());
        if(usuario == null) {
            throw new NegocioException("Usuario não Encontrado");
        }
        var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.getId(), loginDTO.senha());
        try{
            Authentication authentication = this.authenticationManager.authenticate(usernamePassword);
            String token = tokenService.generateToken((Usuario) authentication.getPrincipal());
            return ResponseEntity.accepted().body(AcessoResponseDTO.builder().token(token).build());
        } catch (BadCredentialsException e) {
            throw new NegocioException("Senha incorreta");
        }
    }


    private UsuarioReponseDTO converterUsuarioemDtoRespose(Usuario usuario){
        UsuarioReponseDTO usuarioReponseDTO = UsuarioReponseDTO.builder()
                .idUsuario(usuario.getId())
                .nome(usuario.getNome())
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
                .nome(usuario.getNome())
                .login(usuario.getLogin())
                .build();
        return usuarioCustomReponseDTO;
    }

}
