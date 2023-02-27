package com.br.api.repository;

import com.br.api.domain.model.Interesse;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InteresseRepository extends JpaRepository<Interesse, Long> {

    Optional<Interesse> findFirstByNome(@Param("nome") String nome);


}
