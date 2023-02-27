package com.br.api.mapper;

import com.br.api.domain.model.Usuario;
import com.br.api.domain.request.AtualizarUsuarioRequest;
import org.apache.commons.lang3.function.TriFunction;

public class EntityUpdateUsuarioMapper implements TriFunction<Usuario, AtualizarUsuarioRequest, String, Usuario> {

    @Override
    public Usuario apply(Usuario usuario, AtualizarUsuarioRequest request, String foto) {
        return Usuario.builder()
                .id(usuario.getId())
                .endereco(request.getEndereco())
                .nome(request.getNome())
                .permissions(usuario.getPermissions())
                .telefone(request.getTelefone())
                .avaliacoesRecebidas(usuario.getAvaliacoesRecebidas())
                .senha(usuario.getSenha())
                .email(usuario.getEmail())
                .foto(foto)
                .build();
    }
}
