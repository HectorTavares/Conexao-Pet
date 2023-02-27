package com.br.api.domain.enumaration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Porte {

    GRANDE("Grande"),
    MEDIO("Médio"),
    PEQUENO("Pequeno");

    private final String porte;


}
