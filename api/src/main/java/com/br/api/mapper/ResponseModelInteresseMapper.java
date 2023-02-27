package com.br.api.mapper;


import com.br.api.domain.model.Interesse;
import com.br.api.domain.response.InteresseModelResponse;

import java.util.function.Function;

public class ResponseModelInteresseMapper implements Function<Interesse, InteresseModelResponse> {


    @Override
    public InteresseModelResponse apply(Interesse interesse) {
        return InteresseModelResponse.builder()
                .id(interesse.getId())
                .nome(interesse.getNome())
                .build();
    }
}
