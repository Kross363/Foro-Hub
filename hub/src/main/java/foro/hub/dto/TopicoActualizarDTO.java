package foro.hub.dto;

import jakarta.validation.constraints.NotNull;

public record TopicoActualizarDTO(
                                  //@NotNull
                                  String  titulo,
                                  //@NotNull
                                  String mensaje,
                                  //@NotNull
                                  Boolean status,
                                  //@NotNull
                                  String autor,
                                  //@NotNull
                                  String curso) {
}
