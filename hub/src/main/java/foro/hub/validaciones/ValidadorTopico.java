package foro.hub.validaciones;

import foro.hub.dto.TopicoActualizarDTO;
import foro.hub.dto.TopicoRegistroDTO;
import jakarta.validation.Valid;

public interface ValidadorTopico {
    void validar(TopicoRegistroDTO datos);

    void validar(TopicoActualizarDTO topicoActualizarDTO);
}
