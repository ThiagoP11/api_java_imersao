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

        validarNovoCondomino(condominoDTO);


        return new CondominoDTO(repository.save(new CondominoEntity(condominoDTO)));

    }

    private void validarNovoCondomino(final CondominoDTO condominoDTO) {
        if(repository.findByCpf(condominoDTO.getCpf()).isPresent()){
            throw new RegraDeNegocioException("CPF já cadastrado, utilize a rota de alteração");
        }


        if (repository.findByEmail(condominoDTO.getEmail()).isPresent()){
            throw new RegraDeNegocioException("E-mail já cadastrado, utilize a rota de alteração ou e-mail diferente");
        }
    }

    @PutMapping("/condomino/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CondominoDTO atualizarCondomino(@PathVariable("id") String id, @Valid @RequestBody CondominoDTO condominoDTO){
        condominoDTO.setId(id);

        //CondominoEntity entity = repository.findById(id).orElse(null);

        CondominoEntity entity = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Condomino não encontrado"));

        validarAtualizacao(condominoDTO, entity);

        return new CondominoDTO(repository.save(new CondominoEntity(condominoDTO)));
    }

    private void validarAtualizacao(CondominoDTO condominoDTO, CondominoEntity entity) {
        if(!Objects.equals(entity.getEmail(), condominoDTO.getEmail())){
            if (repository.findByEmail(condominoDTO.getEmail()).isPresent()){
                throw new RegraDeNegocioException("E-mail já cadastrado, utilize um e-mail diferente");
            }
        }

        if (!Objects.equals(entity.getCpf(), condominoDTO.getCpf())){
                throw new RegraDeNegocioException("Não é possivel alterar o CPF");
        }
    }

    @GetMapping("condomino/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public CondominoDTO consultarPorCPF(@PathVariable("cpf") String cpf){
        return new CondominoDTO(repository.findByCpf(cpf).
                orElseThrow(() -> new RecursoNaoEncontradoException("Condomino não localizado")));
    }

    @GetMapping("condomino")
    @ResponseStatus(HttpStatus.OK)
    public List<CondominoDTO> consultarTodo() {
        List<CondominoDTO> dtos= new ArrayList<>();

        repository.findAll().forEach(entity -> {
            dtos.add(new CondominoDTO(entity));
        });

        return dtos;
    }
}
