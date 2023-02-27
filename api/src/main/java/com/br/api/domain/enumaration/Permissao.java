package com.br.api.domain.enumaration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permissao {

    ONG("Ong"),
    VOLUNTARIO("Voluntário");

    private final String permissao;

}
