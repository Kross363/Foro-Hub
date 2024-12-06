package foro.hub.model;

import foro.hub.dto.AutenticacionUsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name="usuario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String clave;

    public Usuario(String login, String clave) {
        this.login=login;
        this.clave=clave;
    }
    public void actualizarDatos(@Valid AutenticacionUsuarioDTO autenticacionUsuarioDTO,PasswordEncoder passwordEncoder){
        if(autenticacionUsuarioDTO.login()!=null){
            this.login= autenticacionUsuarioDTO.login();
        }
        if(autenticacionUsuarioDTO.clave()!=null){
            this.clave=passwordEncoder.encode(autenticacionUsuarioDTO.clave());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //LO QUE HACEMOS CON LA LINEA DE ABAJO ES QUE DESIGNAMOS A ESTA CLASE EL ROL DE USUARIO
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
