package com.br.api.domain.enumaration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Especie {

    CACHORRO("Cachorro"),
    GATO("Gato"),
    COELHO("Coelho"),
    RATO("Rato");

    private final String especie;

}
