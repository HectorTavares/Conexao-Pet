package com.br.api.mapper;

import com.br.api.domain.model.Usuario;
import com.br.api.domain.response.BuscaUsuarioResponse;

import java.util.function.Function;

public class ResponseBuscarUsuarioMapper implements Function<Usuario, BuscaUsuarioResponse> {

    @Override
    public BuscaUsuarioResponse apply(Usuario usuario) {
        return BuscaUsuarioResponse.builder()
                .nome(usuario.getNome())
                .id(usuario.getId())
                .foto(usuario.getFoto())
                .endereco(usuario.getEndereco())
                .isVoluntario(usuario.getPermissions().get(0).getName().equals("Voluntário"))
                .build();
    }
}
