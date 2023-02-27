package com.br.api.repository;

import com.br.api.domain.model.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    @Query(value = "SELECT * FROM campanha c INNER JOIN campanha_interesse ci on c.id = ci.campanha_id INNER JOIN interesse i on i.id = ci.interesse_id INNER JOIN voluntario_interesse vi on vi.interesse_id = ci.interesse_id WHERE vi.voluntario_id = :idVoluntario AND c.data_encerramento > SYSDATE()", nativeQuery = true)
    List<Campanha> buscarFeedPorInteresses(Long idVoluntario);

    @Query(value = "SELECT * FROM campanha c INNER JOIN ong o on c.id_ong = o.id INNER JOIN voluntario_seguido_ong vso on vso.ong_id = o.id WHERE vso.voluntario_id = :idVoluntario AND c.data_encerramento > SYSDATE()", nativeQuery = true)
    List<Campanha> buscarFeedPorOngsSeguidas(Long idVoluntario);

}
