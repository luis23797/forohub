package com.alura.forohub.infra.security.exceptions;

import com.alura.forohub.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarError404(){ return ResponseEntity.notFound().build();}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400(MethodArgumentNotValidException ex){
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new));

    }
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorDeValidacion(ValidacionException e){
        var error = new DatosError(e.getMessage());
        return  ResponseEntity.badRequest().body(error);
    }



    public record DatosErrorValidacion(String campo, String mensaje){
        public DatosErrorValidacion(FieldError err){
            this(
                    err.getField(),
                    err.getDefaultMessage()
            );
        }
    }
    public record DatosError(String error){}

}
