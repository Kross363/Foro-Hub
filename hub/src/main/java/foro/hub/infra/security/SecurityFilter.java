package foro.hub.infra.security;

import foro.hub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component//ANOTACION IMPORTANTE
public class SecurityFilter extends OncePerRequestFilter {//CUANDO IMPLEMENTAMOS ESTA INTERFAZ DE SPRING, IMPLEMENTAR SUS METODOS
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    //FILTER CHAIN (o cadenas de filtros) ES EL ENCARGADO DE INTERCEPTAR LAS SOLICITUDES
    //UNO DE LOS OBJETIVOS ES BLOQUEAR UNA SOLICITUD
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //CON ESTA LINEA DE ABAJO OBTENEMOS EL TOKEN DE Auth DE Insomnia CADA VEZ QUE LANZAMOS EL REQUEST DE Logeo de usuarios
        //EN Auth COLOCAR Auth DE TIPO Bearer token
        var authHeader=request.getHeader("Authorization");
        if(authHeader!=null){
            var token=authHeader.replace("Bearer ","");
           //System.out.println(token);//EN LA CONSOLA NOS DEVUELVE EL TOKEN
           //System.out.println(tokenService.getSubject(token));//EN LA CONSOLA NOS DEVUELVE EL NOMBRE DEL USUARIO (EL TOKEN VERIFICADO)
            var nombreDeUsuario=tokenService.getSubject(token);
            if (nombreDeUsuario!=null) {
                //TOKEN VALIDO
                //SE BUSCA EL USUARIO POR EL SUBJECT(username)
                var usuario=usuarioRepository.findByLogin(nombreDeUsuario);
                var authentication= new UsernamePasswordAuthenticationToken(usuario,null,
                        usuario.getAuthorities());//FORZAMOS UN INICIO DE SESION
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
       }
        filterChain.doFilter(request,response);
    }
}
