package com.br.api.domain.enumaration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genero {

    MACHO("Macho"),
    FEMEA("Femêa");

    private final String genero;
}
