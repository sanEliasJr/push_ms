package br.jus.tjba.api.push.usuario.repository;

import br.jus.tjba.api.push.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u where u.id= :id")
    UserDetails findUsuarioById(Long id);

    @Query("SELECT u FROM Usuario u where u.login = :login")
    Usuario findUsuarioByLogin(String login);
}
