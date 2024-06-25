package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.request.ProcessoDTO;
import br.jus.tjba.api.push.usuario.dto.request.ProcessoSistemaDTO;
import br.jus.tjba.api.push.usuario.dto.request.UsuarioDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioCustomReponseDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioProcessoSistemaResponseDto;
import br.jus.tjba.api.push.usuario.model.Sistema;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import br.jus.tjba.api.push.usuario.pub.service.ConvertTokenService;
import br.jus.tjba.api.push.usuario.repository.UsuarioProcessoSistemaRepository;
import br.jus.tjba.api.push.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public void associarProcessoUsuario(ProcessoSistemaDTO processoSistemaDTO){
        Usuario usuario = convertTokenService.getUsuarioIdFromToken();
        Sistema sistema = sistemaService.findSistemaBySigla(processoSistemaDTO.sistema());
        UsuarioProcessoSistema usuarioProcessoSistema = UsuarioProcessoSistema.builder()
                .usuario(usuario)
                .sistema(sistema)
                .numeroProcesso(processoSistemaDTO.numeroProcesso())
                .build();
        usuarioProcessoSistemaRepository.save(usuarioProcessoSistema);
    }

    public void desassociarProcessoUsuario(ProcessoSistemaDTO processoSistemaDTO){
        Usuario usuario = convertTokenService.getUsuarioIdFromToken();
        Sistema sistema = sistemaService.findSistemaBySigla(processoSistemaDTO.sistema());
        UsuarioProcessoSistema usuarioProcessoSistema = usuarioProcessoSistemaRepository.findUsuarioProcessoSistemaByNumeroProcessoAndUsuarioAndSistema(processoSistemaDTO.numeroProcesso(),sistema.getId(),usuario.getId());
        usuarioProcessoSistemaRepository.delete(usuarioProcessoSistema);
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
