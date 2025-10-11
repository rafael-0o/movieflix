package br.com.movieflix.Config;

import br.com.movieflix.Exeption.Username0rPasswordInvalidExeption;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApllicationControllerAdvice {
    //Use somente quando o atributo da anotação foi declarado como `Class`/`Class[]`
    @ExceptionHandler(Username0rPasswordInvalidExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundExeption(Username0rPasswordInvalidExeption ex){
        return ex.getMessage();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleArgumentNotValidExeption(MethodArgumentNotValidException ex){
        Map<String, String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                (error) ->errors.put(((FieldError)error).getField(), error.getDefaultMessage())
        );
        return errors;
    }
}
