package br.jus.tjba.api.push.usuario.service;

import br.jus.tjba.api.push.usuario.model.Sistema;
import br.jus.tjba.api.push.usuario.repository.SistemaRepositoy;
import br.jus.tjba.api.push.usuario.repository.UsuarioProcessoSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SistemaService {
    @Autowired
    private SistemaRepositoy sistemaRepositoy;

    public Sistema getSistemaById(Long id){
        return sistemaRepositoy.findById(id).orElse(null);
    }

    public Sistema findSistemaBySigla(String sigla) {
        return sistemaRepositoy.findBySigla(sigla).orElse(null);
    }
}
