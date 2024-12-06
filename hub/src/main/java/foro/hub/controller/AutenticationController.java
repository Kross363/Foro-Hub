package foro.hub.controller;

import foro.hub.dto.AutenticacionUsuarioDTO;
import foro.hub.dto.JWTtokenDTO;
import foro.hub.infra.security.TokenService;
import foro.hub.model.Usuario;
import foro.hub.validaciones.ValidadorTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/login")
public class AutenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AutenticacionUsuarioDTO dto){
        Authentication authToken=new UsernamePasswordAuthenticationToken(dto.login(),dto.clave());
        var usuarioAutenticado=authenticationManager.authenticate(authToken);
        var jwtToken=tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTtokenDTO(jwtToken));
    }
}
