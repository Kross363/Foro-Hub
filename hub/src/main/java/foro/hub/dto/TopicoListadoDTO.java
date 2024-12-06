package foro.hub.dto;

import foro.hub.model.Topico;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public record TopicoListadoDTO(@NotNull
                               Long id,
                               @NotNull
                               String titulo,
                               @NotNull
                               String mensaje,
                               @NotNull
                               LocalDateTime fecha,
                               @NotNull
                               Boolean status,
                               @NotNull
                               String autor,
                               @NotNull
                               String curso) {
    //CONSTRUCTOR CREADO PARA METODO mostrarTopicos
    public TopicoListadoDTO(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(),topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
