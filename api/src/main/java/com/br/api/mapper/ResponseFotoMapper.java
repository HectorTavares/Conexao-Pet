package com.br.api.mapper;

import com.br.api.domain.model.Foto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseFotoMapper implements Function<Foto, String> {

    @Override
    public String apply(Foto foto) {
        return foto.getUrl();
    }

    public List<String> apply(List<Foto> foto) {
        return foto.stream().map(Foto::getUrl).collect(Collectors.toList());
    }

}
