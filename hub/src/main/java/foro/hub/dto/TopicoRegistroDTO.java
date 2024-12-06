package foro.hub.dto;

import jakarta.validation.constraints.NotNull;

public record TopicoRegistroDTO(@NotNull
                                Long id,
                                @NotNull
                                String mensaje,
                                @NotNull
                                String curso,
                                @NotNull
                                String titulo) {
}
