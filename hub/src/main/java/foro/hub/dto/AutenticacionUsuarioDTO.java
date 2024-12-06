package foro.hub.dto;

import foro.hub.model.Usuario;

public record AutenticacionUsuarioDTO(String login,
                                      String clave) {
    public AutenticacionUsuarioDTO(Usuario usuario){
        this(usuario.getLogin(),usuario.getClave());
    }
}
