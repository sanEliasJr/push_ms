package br.jus.tjba.api.push.publicador.repository;

import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends JpaRepository<MensagemPendente, Long> {
}
