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
public class CondominoEntrada {
    @NotBlank
    private String nome;
    @NotNull
    private Integer dddCelular;
    @NotNull
    private Integer numeroCelular;
    @NotBlank
    private String email;
    @NotBlank
    private String bloco;
    @NotBlank
    private String apto;
    @NotBlank
    private String cpf;




}
