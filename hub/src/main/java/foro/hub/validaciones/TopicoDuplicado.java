package foro.hub.validaciones;

import foro.hub.dto.TopicoActualizarDTO;
import foro.hub.dto.TopicoRegistroDTO;
import foro.hub.infra.errores.ValidacionException;
import foro.hub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoDuplicado implements ValidadorTopico{
    @Autowired
    private TopicoRepository topicoRepository;
    @Override
    public void validar(TopicoRegistroDTO datos) {
        var mismoMensajeYTiulo=topicoRepository.existsByTituloAndMensaje(datos.titulo(),datos.mensaje());
        if(mismoMensajeYTiulo){
            throw new ValidacionException("No se puede crear un topico que ya existe");
        }
    }

    @Override
    public void validar(TopicoActualizarDTO topicoActualizarDTO) {

    }
}
