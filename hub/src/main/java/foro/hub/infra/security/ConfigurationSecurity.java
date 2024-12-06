package foro.hub.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration//IMPORTANTE ANOTACION
@EnableWebSecurity//IMPORTANTE ANOTACION
public class ConfigurationSecurity {
    @Autowired
    private SecurityFilter securityFilter;
    //IMPORTANTE
    //TODO LO DE ABAJO SE COPIA Y PEGA Y SE IMPORTAN SUS CLASES
    //IMPORTANTE
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario").permitAll()
                        //LA LINEA DE ARRIBA INDICA QUE LOS METODOS POST Y QUE TENGA /LOGIN EN LA URL PUEDA ENTRAR CUALQUIERA
                        .requestMatchers("/v3/api-docs/**","/swagger-ui.html","/swagger-ui/**").permitAll()
                        //LA LINEA DE ARRIBA ES PARA LIBERAR LAS URLS QUE SE GENERAN DE SpringDoc (ver .txt sobre la dependencia)
                        .anyRequest().authenticated()
                ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);//ESTO INDICA QUE PARA CADA OTRO REQUEST NECESITA ESTAR AUTENTICADO
        return http.build();

    }
    //SINO CREAMOS ESTE METODO NOS DARA UN ERROR LA APLICACION DEL AutenticacionController
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    //METODO PARA LA ENCRIPTACION DE LA CLAVE (HASH DE PASSWORD SE LO DENOMINA)
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
