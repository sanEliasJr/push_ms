package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.request.EnderecoUsuarioDTO;
import br.jus.tjba.api.push.usuario.dto.local.PersisteEnderecoUsuarioDTO;
import br.jus.tjba.api.push.usuario.dto.response.EnderecoUsuarioResponseDTO;
import br.jus.tjba.api.push.usuario.dto.response.UsuarioReponseDTO;
import br.jus.tjba.api.push.usuario.model.EnderecoUsuario;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.repository.EnderecoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoUsuarioService {
    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

    public EnderecoUsuario getUsuarioById(Long id){
        return enderecoUsuarioRepository.findById(id).orElse(null);
    }

//    public EnderecoUsuario saveUsuario(EnderecoUsuarioDTO enderecoUsuarioDTO){
//        EnderecoUsuario enderecoUsuario = EnderecoUsuario.builder()
//                .rua(enderecoUsuarioDTO.rua())
//                .bairro(enderecoUsuarioDTO.bairro())
//                .cidade(enderecoUsuarioDTO.cidade())
//                .cep(enderecoUsuarioDTO.cep())
//                .uf(enderecoUsuarioDTO.uf())
//                .numero(enderecoUsuarioDTO.numero())
//                .usuario(usuarioService.getUsuarioById(enderecoUsuarioDTO.idUsuario()))
//                .build();
//
//        return enderecoUsuarioRepository.save(enderecoUsuario);
//    }

    public void deleteUsuario(Long id){
        enderecoUsuarioRepository.deleteById(id);
    }


    public List<EnderecoUsuario> criaListaEnderecoUsuario(PersisteEnderecoUsuarioDTO persisteEnderecoUsuarioDTO){
        List<EnderecoUsuarioDTO> enderecoUsuarioDTO = persisteEnderecoUsuarioDTO.enderecoUsuarioDTOList();
        List<EnderecoUsuario> listaEnderecoUsuario = new ArrayList<EnderecoUsuario>();
        for(EnderecoUsuarioDTO enderecoUsuarioDTOAux : enderecoUsuarioDTO){
            EnderecoUsuario enderecoUsuario = EnderecoUsuario.builder()
                    .rua(enderecoUsuarioDTOAux.rua())
                    .cidade(enderecoUsuarioDTOAux.cidade())
                    .cep(enderecoUsuarioDTOAux.cep())
                    .uf(enderecoUsuarioDTOAux.uf())
                    .bairro(enderecoUsuarioDTOAux.bairro())
                    .numero(enderecoUsuarioDTOAux.numero())
                    .usuario(persisteEnderecoUsuarioDTO.usuario())
                    .build();
            listaEnderecoUsuario.add(enderecoUsuario);
        }
        return listaEnderecoUsuario;
    }

    public EnderecoUsuarioResponseDTO converterEnderecoUsuarioemDtoRespose(EnderecoUsuario enderecoUsuario){
        EnderecoUsuarioResponseDTO enderecoResponseDTO = EnderecoUsuarioResponseDTO.builder()
                .idEndereco(enderecoUsuario.getId())
                .rua(enderecoUsuario.getRua())
                .cidade(enderecoUsuario.getCidade())
                .bairro(enderecoUsuario.getCidade())
                .numero(enderecoUsuario.getNumero())
                .cep(enderecoUsuario.getCep())
                .uf(enderecoUsuario.getUf())
                .build();
        return enderecoResponseDTO;
    }

}
