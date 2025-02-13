package com.imersao.primeira_api.primeira_api;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Lombok -> O Lombok é uma biblioteca Java que ajuda a automatizar a geração de código, o que pode ser útil para o Spring

@Setter
@Getter
@AllArgsConstructor //cria um construtor que recebe como parâmetros todos os atributos da classe
@NoArgsConstructor //cria para a gente aquele construtor padrão, que não recebe nenhum argumento
//@Getter //metodo getter será criado automaticamente
//@Setter // metodo setter será criado automaticamente
public class PrimeiroContrato {

    @Size(min = 3, max = 10, message = "o nome deve ter entre 3 e 10 caracteres")
    private String nome;
    private String sobrenome;

}
