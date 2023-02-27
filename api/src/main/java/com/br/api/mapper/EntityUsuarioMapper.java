package com.br.api.mapper;

import com.br.api.domain.model.Usuario;
import com.br.api.domain.request.CadastrarUsuarioRequest;

import java.util.function.Function;

public class EntityUsuarioMapper implements Function<CadastrarUsuarioRequest, Usuario> {

    @Override
    public Usuario apply(CadastrarUsuarioRequest request) {
        return Usuario.builder()
                .email(request.getEmail())
                .senha(request.getSenha())
                .nome(request.getNome())
                .endereco(request.getEndereco())
                .telefone(request.getTelefone())
                .build();
    }
}
