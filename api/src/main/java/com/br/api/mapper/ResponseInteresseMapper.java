package com.br.api.mapper;

import com.br.api.domain.model.Interesse;
import com.br.api.domain.model.Voluntario;
import com.br.api.domain.response.InteresseModelResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseInteresseMapper implements Function<Voluntario, List<InteresseModelResponse>> {


    @Override
    public List<InteresseModelResponse> apply(Voluntario voluntario) {
        return voluntario.getInteresses().stream().map(this::toInteresseResponse).collect(Collectors.toList());
    }

    private InteresseModelResponse toInteresseResponse(Interesse interesse) {
        return InteresseModelResponse.builder()
                .id(interesse.getId())
                .nome(interesse.getNome())
                .build();
    }
}
