package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.dto.SistemaDTO;
import br.jus.tjba.api.push.usuario.dto.UsuarioDTO;
import br.jus.tjba.api.push.usuario.model.Sistema;
import br.jus.tjba.api.push.usuario.model.Usuario;
import br.jus.tjba.api.push.usuario.repository.SistemaRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SistemaService {
    @Autowired
    private SistemaRepositoy sistemaRepositoy;

    public Sistema getSistemaById(Long id){
        return sistemaRepositoy.findById(id).orElse(null);
    }

    public Sistema saveSistema(SistemaDTO sistemaDTO){
        Sistema sistema = Sistema.builder()
                .sigla(sistemaDTO.sigla())
                .build();

        return sistemaRepositoy.save(sistema);
    }

    public void deleteSistema(Long id){
        sistemaRepositoy.deleteById(id);
    }
}
