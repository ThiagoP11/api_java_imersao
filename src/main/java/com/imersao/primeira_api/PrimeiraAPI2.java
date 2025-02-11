package com.imersao.primeira_api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("/primeira_chamada2")
@RestController // ele aborta o proprio controler e o ResponseBody
public class PrimeiraAPI2 {

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


}
