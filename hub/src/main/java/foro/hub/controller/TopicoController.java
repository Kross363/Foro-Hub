package foro.hub.controller;

import foro.hub.dto.TopicoActualizarDTO;
import foro.hub.dto.TopicoListadoDTO;
import foro.hub.dto.TopicoRegistroDTO;
import foro.hub.dto.TopicoRespuestaDTO;
import foro.hub.model.Topico;
import foro.hub.repository.TopicoRepository;
import foro.hub.validaciones.ValidadorTopico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private List<ValidadorTopico> validadores;
    @Autowired
    private TopicoRepository topicoRepository;
    //CREAR UN TOPICO
    //{"id":"1",
    //"mensaje":"Error al ejecutar una requisicion",
    //"curso":"Java back-end",
    //"titulo":"Error requisicion"}
    //http://localhost:8080/topico
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoRespuestaDTO> crearTopico(@RequestBody @Valid TopicoRegistroDTO json,
                                                          UriComponentsBuilder uriComponentsBuilder,
                                                          Principal principal){
        validadores.forEach(v-> v.validar(json));
        var fecha=LocalDateTime.now();
        String username=principal.getName().replace("."," ");
        String[] palabras = username.split(" ");
        StringBuilder nombreFormateado = new StringBuilder();

        for (String palabra : palabras) {
            String palabraFormateada = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
            nombreFormateado.append(palabraFormateada).append(" ");
        }
        Topico topico=new Topico(json,fecha,nombreFormateado);
        topicoRepository.save(topico);
        TopicoRespuestaDTO topicoRespuestaDTO=new TopicoRespuestaDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(),topico.getStatus(),
                topico.getAutor(),topico.getCurso());
        URI url=uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(topicoRespuestaDTO);
    }
    //DEVOLVER TODOS LOS TOPICOS
    //http://localhost:8080/topico
    @GetMapping
    public ResponseEntity<Page<TopicoListadoDTO>> mostrarTopicos(@PageableDefault() Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAllByOrderByFechaAsc(paginacion).map(TopicoListadoDTO::new));
    }
    //DEVOLVER UN TOPICO EN ESPECIFICO PASANDOLE EL ID
    //http://localhost:8080/topico/{id}
    @GetMapping("/{id}")
    public ResponseEntity detalleTopico(@PathVariable Long id){
        Topico topico=topicoRepository.getReferenceById(id);
        var datosTopico=new TopicoRespuestaDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(),topico.getStatus(),
                topico.getAutor(),topico.getCurso());
        return ResponseEntity.ok(datosTopico);
    }
    //ACTUALIZAR UN TOPICO
    //http://localhost:8080/topico/{id}
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid TopicoActualizarDTO topicoActualizarDTO,
                                           @PathVariable Long id){
        validadores.forEach(v-> v.validar(topicoActualizarDTO));
        Topico topico=topicoRepository.getReferenceById(id);
        topico.actualizarDatos(topicoActualizarDTO);
        return ResponseEntity.ok(new TopicoRespuestaDTO(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFecha(),topico.getStatus(),
                topico.getAutor(),topico.getCurso()));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id){
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
