package foro.hub.repository;

import foro.hub.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    UserDetails findByLogin(String username);

    Page<Usuario> findAllByOrderByLogin(Pageable paginacion);

    boolean existsByLogin(String login);
}
