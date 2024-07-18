package br.jus.tjba.api.push.publicador.repository;

import br.jus.tjba.api.push.publicador.model.MensagemPendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MensagemRepository extends JpaRepository<MensagemPendente, Long> {

    @Query("select mp from MensagemPendente mp where mp.email = :email and mp.numeroProcesso = :numeroProcesso")
    MensagemPendente findByEmailAndProcessoAndSigla(String numeroProcesso, String email);
}
