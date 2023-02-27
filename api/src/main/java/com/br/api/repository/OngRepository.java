package com.br.api.repository;

import com.br.api.domain.model.Ong;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long> {

    @Query(value = "SELECT * FROM ong o INNER JOIN usuario u ON u.id = o.user_id where u.id = :id", nativeQuery = true)
    Optional<Ong> findByIdUsuario(@Param(value = "id") final Long id);

}
