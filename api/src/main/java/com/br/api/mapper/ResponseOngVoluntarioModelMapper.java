package com.br.api.mapper;

import com.br.api.domain.model.Ong;
import com.br.api.domain.model.Voluntario;
import com.br.api.domain.response.OngModelResponse;
import com.br.api.domain.response.OngVoluntarioModelResponse;
import com.br.api.domain.response.VoluntarioModelReponse;

import java.util.function.BiFunction;

import static java.util.Objects.isNull;

public class ResponseOngVoluntarioModelMapper implements BiFunction<Ong, Voluntario, OngVoluntarioModelResponse> {

    private final ResponseOngMapper responseOngMapper = new ResponseOngMapper();

    private final ResponseVoluntarioMapper responseVoluntarioMapper = new ResponseVoluntarioMapper();

    @Override
    public OngVoluntarioModelResponse apply(Ong ong, Voluntario voluntario) {
        return OngVoluntarioModelResponse.builder()
                .ongModelResponse(getOngModelResponse(ong))
                .voluntarioModelReponse(getVoluntarioResponse(voluntario))
                .build();
    }

    private OngModelResponse getOngModelResponse(Ong ong) {
        if (isNull(ong)) {
            return null;
        }

        return responseOngMapper.apply(ong);
    }

    private VoluntarioModelReponse getVoluntarioResponse(Voluntario voluntario) {
        if (isNull(voluntario)) {
            return null;
        }

        return responseVoluntarioMapper.apply(voluntario);

    }
}
