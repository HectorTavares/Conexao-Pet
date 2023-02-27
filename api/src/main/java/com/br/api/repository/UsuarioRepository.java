package com.br.api.repository;

import com.br.api.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    @Query(value = "SELECT DISTINCT u.* FROM usuario u INNER JOIN permission p ON p.user_id = u.id WHERE u.nome like lower(concat('%',:nome,'%')) AND u.id != :id AND p.name = :nomePermission", nativeQuery = true)
    List<Usuario> buscarUsuariosPorNomeEPermissao(String nome, Long id, String nomePermission);

}
