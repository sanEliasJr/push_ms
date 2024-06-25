package br.jus.tjba.api.push.usuario.repository;

import br.jus.tjba.api.push.usuario.model.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SistemaRepositoy extends JpaRepository<Sistema, Long> {
    @Query("select s from Sistema s where s.sigla = :sigla")
    Optional<Sistema> findBySigla(String sigla);
}
