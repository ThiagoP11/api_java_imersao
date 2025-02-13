package com.imersao.primeira_api.condominio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class CondominoController {

    @Autowired
    private CondominoRepository repository;

    @PostMapping("/condomino")
    @ResponseStatus(HttpStatus.CREATED)
    public CondominoDTO cadastrarCondomino(@Valid @RequestBody CondominoDTO condominoDTO){

        if(repository.findByCpf(condominoDTO.getCpf()).isPresent()){
            throw new RegraDeNegocioException("CPF já cadastrado, utilize a rota de alteração");
        }


        if (repository.findByEmail(condominoDTO.getEmail()).isPresent()){
            throw new RegraDeNegocioException("E-mail já cadastrado, utilize a rota de alteração ou e-mail diferente");
        }


        CondominoEntity entity = new CondominoEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setApto(condominoDTO.getApto());
        entity.setCpf(condominoDTO.getCpf());
        entity.setBloco(condominoDTO.getBloco());
        entity.setEmail(condominoDTO.getEmail());
        entity.setNome(condominoDTO.getNome());
        entity.setDddCelular(condominoDTO.getDddCelular());
        entity.setNumeroCelular(condominoDTO.getNumeroCelular());

        CondominoEntity entitySalva = repository.save(entity);
        condominoDTO.setId(entitySalva.getId());

        return condominoDTO;

    }

    @PutMapping("/condomino/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CondominoDTO atualizarCondomino(@PathVariable("id") String id, @Valid @RequestBody CondominoDTO condominoDTO){
        condominoDTO.setId(id);

        //CondominoEntity entity = repository.findById(id).orElse(null);

        CondominoEntity entity = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Condomino não encontrado"));

        if(!Objects.equals(entity.getEmail(), condominoDTO.getEmail())){
            if (repository.findByEmail(condominoDTO.getEmail()).isPresent()){
                throw new RegraDeNegocioException("E-mail já cadastrado, utilize um e-mail diferente");
            }
        }

        if (!Objects.equals(entity.getCpf(), condominoDTO.getCpf())){
                throw new RegraDeNegocioException("Não é possivel alterar o CPF");
        }

        entity.setApto(condominoDTO.getApto());
        entity.setCpf(condominoDTO.getCpf());
        entity.setBloco(condominoDTO.getBloco());
        entity.setEmail(condominoDTO.getEmail());
        entity.setNome(condominoDTO.getNome());
        entity.setDddCelular(condominoDTO.getDddCelular());
        entity.setNumeroCelular(condominoDTO.getNumeroCelular());

        repository.save(entity);

        return condominoDTO;
    }

    @GetMapping("condomino/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public CondominoDTO consultarPorCPF(@PathVariable("cpf") String cpf){
        CondominoEntity entity = repository.findByCpf(cpf).
                orElseThrow(() -> new RecursoNaoEncontradoException("Condomino não localizado"));

        CondominoDTO dto = new CondominoDTO();

        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setApto(entity.getApto());
        dto.setNome(entity.getNome());
        dto.setBloco(entity.getBloco());
        dto.setDddCelular(entity.getDddCelular());
        dto.setNumeroCelular(entity.getNumeroCelular());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    @GetMapping("condomino")
    @ResponseStatus(HttpStatus.OK)
    public List<CondominoDTO> consultarTodo() {
        List<CondominoEntity> entities = repository.findAll();
        List<CondominoDTO> dtos= new ArrayList<>();
        entities.forEach(entity -> {
            CondominoDTO dto = new CondominoDTO();


            dto.setId(entity.getId());
            dto.setCpf(entity.getCpf());
            dto.setApto(entity.getApto());
            dto.setNome(entity.getNome());
            dto.setBloco(entity.getBloco());
            dto.setDddCelular(entity.getDddCelular());
            dto.setNumeroCelular(entity.getNumeroCelular());
            dto.setEmail(entity.getEmail());

            dtos.add(dto);
        });

        return dtos;
    }
}
