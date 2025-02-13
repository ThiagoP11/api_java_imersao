package com.imersao.primeira_api.condominio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondominoSaida {

    private String id;

    private String nome;

    private Integer dddCelular;

    private Integer numeroCelular;

    private String email;

    private String bloco;

    private String apto;

    private String cpf;




}
