package foro.hub.model;

import foro.hub.dto.TopicoActualizarDTO;
import foro.hub.dto.TopicoRegistroDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="topico")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Boolean status;
    private String autor;
    private String curso;

    public Topico(@Valid TopicoRegistroDTO json, LocalDateTime fecha,  StringBuilder nombreFormateado) {
        this.status=true;
        this.titulo=json.titulo();
        this.mensaje= json.mensaje();
        this.fecha=fecha;
        this.autor=nombreFormateado.toString();
        this.curso= json.curso();
    }


    public void actualizarDatos(@Valid TopicoActualizarDTO topicoActualizarDTO) {
        //SE PREGUNTA SI ES DISTINTO DE NULL POR SI QUEREMOS CAMBIAR SOLO UN PARAMETRO
        //EN INSOMNIA TENDREMOS QUE COLOCAR UN BODY ASI
        //http://localhost:8080/topico/{id}
        //"lo que queremos actualizar (titulo,mensaje,status)":"nombre,dni o direccion"}
        if(topicoActualizarDTO.titulo()!=null){
            this.titulo=topicoActualizarDTO.titulo();
        }
        if(topicoActualizarDTO.mensaje()!=null){
            this.mensaje=topicoActualizarDTO.mensaje();
        }
        if(topicoActualizarDTO.status()!=null){
            this.status=topicoActualizarDTO.status();
        }
        if(topicoActualizarDTO.autor()!=null){
            this.autor=topicoActualizarDTO.autor();
        }
        if(topicoActualizarDTO.curso()!=null){
            this.curso=topicoActualizarDTO.curso();
        }
    }
}
