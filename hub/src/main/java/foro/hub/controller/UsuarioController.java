package foro.hub.controller;

import foro.hub.dto.AutenticacionUsuarioDTO;
import foro.hub.dto.TopicoActualizarDTO;
import foro.hub.dto.TopicoListadoDTO;
import foro.hub.model.Usuario;
import foro.hub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping
    @Transactional
    //http://localhost:8080/usuario
    public ResponseEntity registrarUsuarios(@RequestBody @Valid AutenticacionUsuarioDTO dto){
        if (usuarioRepository.existsByLogin(dto.login())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El usuario ya existe");
        }
        Usuario usuarioNuevo=new Usuario(dto.login(), passwordEncoder.encode(dto.clave()));
        usuarioRepository.save(usuarioNuevo);
        return ResponseEntity
                .status(HttpStatus.CREATED) // Código HTTP 201
                .body(usuarioNuevo);
    }
    @GetMapping
    //http://localhost:8080/usuario
    public ResponseEntity<Page<AutenticacionUsuarioDTO>> mostrarUsuarios(@PageableDefault() Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findAllByOrderByLogin(paginacion).map(AutenticacionUsuarioDTO::new));
    }
    @PutMapping("/{id}")
    @Transactional
    //http://localhost:8080/usuario/id
    public ResponseEntity actualizarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO autenticacionUsuarioDTO,
                                            @PathVariable Long id){
        Usuario usuario=usuarioRepository.getReferenceById(id);
        usuario.actualizarDatos(autenticacionUsuarioDTO,passwordEncoder);
        return ResponseEntity.ok(new AutenticacionUsuarioDTO(usuario.getLogin(), usuario.getClave()));
    }
    @DeleteMapping("/{id}")
    @Transactional
    //http://localhost:8080/usuario/id
    public ResponseEntity borrarUsuario(@PathVariable Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
