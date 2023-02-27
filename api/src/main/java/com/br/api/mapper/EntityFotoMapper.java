package com.br.api.mapper;

import com.br.api.domain.model.Foto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityFotoMapper implements Function<String, Foto> {

    @Override
    public Foto apply(String s) {
        return Foto.builder()
                .url(s)
                .build();
    }

    public List<Foto> apply(List<String> fotos) {
        return fotos.stream().map(this::apply).collect(Collectors.toList());
    }
}
