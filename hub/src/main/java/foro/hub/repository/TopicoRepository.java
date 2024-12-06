package foro.hub.repository;

import foro.hub.dto.TopicoListadoDTO;
import foro.hub.model.Topico;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    boolean existsByTituloAndMensaje(@NotNull String titulo, @NotNull String mensaje);

    Page<Topico> findAllByOrderByFechaAsc(Pageable paginacion);
}
