package br.jus.tjba.api.push.usuario.repository;

import br.jus.tjba.api.push.usuario.model.EnderecoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuario, Long> {
}
