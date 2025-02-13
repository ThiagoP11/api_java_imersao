package com.imersao.primeira_api.condominio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominoRepository extends JpaRepository<CondominoEntity, String> {

    Optional<CondominoEntity> findByCpf(String cpf);
    Optional<CondominoEntity> findByEmail(String email);
}
