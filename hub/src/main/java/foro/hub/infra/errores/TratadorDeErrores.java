package foro.hub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {
    //COLOCAR ESTA ANOTACION Y COMO PARAMETRO EL TIPO DE ERROR QUE TENEMOS
    //EN ESTE CASO EL ERROR ES EntityNotFoundException QUE ES QUE NO ENCONTRO UN ID
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity trataError404(){
        return ResponseEntity.notFound().build();
    }
    //ESTOS TIPOS DE ERRORES SE ENCUENTRAN EN LOS LOGS CUANDO EJECUTAMOS LA APLICACION
    //EN ESTE CASO EL ERROR MethodArgumentNotValidException SUCEDE CUANDO REGISTRAMOS UN MEDICO Y FALTA ALGUN DATO (EMAIL,DNI,NOMBRE,ETC)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity trataError400(MethodArgumentNotValidException e){
        var errores=e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity trataErrorDeValidacion(ValidacionException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    //CONSTRUCTOR NECESARIO PARA var errores CUANDO HACEMOS LA LISTA DE ERRORES CON ESA VARIABLE CON .stream()
    private record DatosErrorValidacion(String campo,
                                        String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
