package com.imersao.primeira_api.condominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CondominoEntity {

    @Id
    private String id;

    @Column
    private String nome;

    @Column
    private Integer dddCelular;

    @Column
    private Integer numeroCelular;

    @Column
    private String email;

    @Column
    private String bloco;

    @Column
    private String apto;

    @Column
    private String cpf;

    public CondominoEntity(CondominoDTO condominoDTO) {
        this.id = UUID.randomUUID().toString();
        this.apto = condominoDTO.getApto();
        this.cpf = condominoDTO.getCpf();
        this.bloco = condominoDTO.getBloco();
        this.email = condominoDTO.getEmail();
        this.nome = condominoDTO.getNome();
        this.dddCelular = condominoDTO.getDddCelular();
        this.numeroCelular = condominoDTO.getNumeroCelular();
    }


}
