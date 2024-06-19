package br.jus.tjba.api.push.usuario.repository;

import br.jus.tjba.api.push.usuario.model.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SistemaRepositoy extends JpaRepository<Sistema, Long> {
}
