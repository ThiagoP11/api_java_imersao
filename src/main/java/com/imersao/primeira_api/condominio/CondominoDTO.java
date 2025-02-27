package com.imersao.primeira_api.condominio;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondominoDTO {

    private String id;

    @NotBlank
    private String nome;
    @NotNull
    private Integer dddCelular;
    @NotNull
    private Integer numeroCelular;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String bloco;
    @NotBlank
    private String apto;
    @NotBlank
    @Size(min = 11, max = 11, message = "Informa apenas os 11 números do CPF (Sem pontos ou traços)")
    private String cpf;

    public CondominoDTO(CondominoEntity condominoEntity) {
        this.id = UUID.randomUUID().toString();
        this.apto = condominoEntity.getApto();
        this.cpf = condominoEntity.getCpf();
        this.bloco = condominoEntity.getBloco();
        this.email = condominoEntity.getEmail();
        this.nome = condominoEntity.getNome();
        this.dddCelular = condominoEntity.getDddCelular();
        this.numeroCelular = condominoEntity.getNumeroCelular();
    }




}
