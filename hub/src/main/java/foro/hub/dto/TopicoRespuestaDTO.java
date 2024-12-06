package foro.hub.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoRespuestaDTO(@NotNull
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
}
