package com.imersao.primeira_api.primeira_api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/primeira_chamada")
@RestController // ele aborta o proprio controler e o ResponseBody
public class PrimeiraAPI {

    @GetMapping("/ola_mundo")
    public String ola() {
        if(LocalDateTime.now().getSecond() / 2 == 0){
            return "Olá Mundo!";
        }else {
            return "thiago";
        }

    }

    @PostMapping
    public String post(@Valid @RequestBody PrimeiroContrato primeiroContrato){
        return primeiroContrato.getNome() + " " + primeiroContrato.getSobrenome();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest()
                .body(ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
