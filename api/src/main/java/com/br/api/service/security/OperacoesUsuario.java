package com.br.api.service.security;

import com.br.api.domain.enumaration.Permissao;
import com.br.api.domain.model.Usuario;

public interface OperacoesUsuario<OUT> {

    Permissao getPermissao();

    OUT executar(Usuario usuario);

}
