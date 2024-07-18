package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.request.ProcessoDTO;
import br.jus.tjba.api.push.usuario.dto.request.ProcessoSistemaDTO;
import br.jus.tjba.api.push.usuario.dto.request.UsuarioDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioCustomReponseDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioProcessoSistemaResponseDto;
import br.jus.tjba.api.push.usuario.model.Sistema;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import br.jus.tjba.api.push.usuario.pub.exceptions.NegocioException;
import br.jus.tjba.api.push.usuario.pub.service.ConvertTokenService;
import br.jus.tjba.api.push.usuario.pub.service.ResponseService;
import br.jus.tjba.api.push.usuario.repository.UsuarioProcessoSistemaRepository;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioProcessoSistemaService {

    @Autowired
    private UsuarioProcessoSistemaRepository usuarioProcessoSistemaRepository;
    @Autowired
    private SistemaService sistemaService;
    @Autowired
    private ConvertTokenService convertTokenService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    ResponseService responseService;

    @Transactional
    public ResponseEntity<Object> associarProcessoUsuario(ProcessoSistemaDTO processoSistemaDTO){
        Usuario usuario = convertTokenService.getUsuarioIdFromToken();
        if(usuario == null){
            throw new NegocioException("Usuario não encontrado!");
        }
        Sistema sistema = sistemaService.findSistemaBySigla(processoSistemaDTO.sistema());
        if(sistema == null){
            throw new NegocioException("Sistema não encontrado");
        }
        UsuarioProcessoSistema usuarioProcessoSistema = usuarioProcessoSistemaRepository.findUsuarioProcessoSistemaByNumeroProcessoAndUsuarioAndSistema(processoSistemaDTO.numeroProcesso(),
                sistema.getId(),
                usuario.getId());

        if (usuarioProcessoSistema != null) {
           throw new NegocioException("Usuario já associado!");
        }
        usuarioProcessoSistema = UsuarioProcessoSistema.builder()
                .usuario(usuario)
                .sistema(sistema)
                .numeroProcesso(processoSistemaDTO.numeroProcesso())
                .build();
        usuarioProcessoSistemaRepository.save(usuarioProcessoSistema);
        return responseService.sendMessage("Usuario associado!", HttpStatus.CREATED);
    }
    @Transactional
    public ResponseEntity<Object> desassociarProcessoUsuario(ProcessoSistemaDTO processoSistemaDTO){
        Usuario usuario = convertTokenService.getUsuarioIdFromToken();
        Sistema sistema = sistemaService.findSistemaBySigla(processoSistemaDTO.sistema());
        UsuarioProcessoSistema usuarioProcessoSistema = usuarioProcessoSistemaRepository.findUsuarioProcessoSistemaByNumeroProcessoAndUsuarioAndSistema(processoSistemaDTO.numeroProcesso(),sistema.getId(),usuario.getId());
        usuarioProcessoSistemaRepository.delete(usuarioProcessoSistema);
        return responseService.sendMessage("Usuario desassociado!", HttpStatus.NO_CONTENT);
    }

    public UsuarioProcessoSistemaResponseDto buscarUsuarioAssociadoProcesso(ProcessoSistemaDTO processoSistemaDTO){
        Sistema sistema = sistemaService.findSistemaBySigla(processoSistemaDTO.sistema());
        List<UsuarioProcessoSistema> usuarioProcessoSistema = usuarioProcessoSistemaRepository.findUsuarioProcessoSistemaByNumeroProcessoAndSistema(processoSistemaDTO.numeroProcesso(),sistema.getId());
        return !usuarioProcessoSistema.isEmpty() ? converterUsuarioProcessoSistemaEmDtoResponse(usuarioProcessoSistema) : null;
    }

    private UsuarioProcessoSistemaResponseDto converterUsuarioProcessoSistemaEmDtoResponse (List<UsuarioProcessoSistema> usuarioProcessoSistema){
        List<UsuarioCustomReponseDTO> usuarios = new ArrayList<>();
        for(UsuarioProcessoSistema u : usuarioProcessoSistema){
            usuarios.add(usuarioService.converterUsuarioemDtoResponse(u.getUsuario()));
        }
        UsuarioProcessoSistemaResponseDto usuarioProcessoSistemaResponseDto = UsuarioProcessoSistemaResponseDto.builder()
                .sistema(usuarioProcessoSistema.get(0).getSistema().getSigla())
                .numeroProcesso(usuarioProcessoSistema.get(0).getNumeroProcesso())
                .usuarios(usuarios)
                .build();
        return usuarioProcessoSistemaResponseDto;
    }

}
