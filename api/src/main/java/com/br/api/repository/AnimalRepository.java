package com.br.api.repository;

import com.br.api.domain.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query(value = "SELECT DISTINCT a.* FROM animal a WHERE a.nome like lower(concat('%',:busca,'%')) OR a.raca like lower(concat('%',:busca,'%')) OR a.especie like  lower(concat('%',:busca,'%'))", nativeQuery = true)
    List<Animal> buscarAnimaisPorNomeRaca(String busca);

}
