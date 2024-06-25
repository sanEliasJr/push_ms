package br.jus.tjba.api.push.usuario.repository;

import br.jus.tjba.api.push.usuario.model.UsuarioProcessoSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioProcessoSistemaRepository extends JpaRepository<UsuarioProcessoSistema, Long> {
    @Query("select ups from UsuarioProcessoSistema ups where ups.numeroProcesso = :numeroProcesso and ups.sistema.id = :sistemaId and ups.usuario.id = :usuarioId")
    UsuarioProcessoSistema findUsuarioProcessoSistemaByNumeroProcessoAndUsuarioAndSistema(String numeroProcesso, Long sistemaId, Long usuarioId);

    @Query("select ups from UsuarioProcessoSistema ups where ups.sistema.id = :sistemaId and ups.numeroProcesso = :numeroProcesso")
    List<UsuarioProcessoSistema> findUsuarioProcessoSistemaByNumeroProcessoAndSistema(String numeroProcesso, Long sistemaId);
}
