package com.br.api.mapper;

import com.br.api.domain.model.Ong;
import com.br.api.domain.response.DonoCampanhaResponse;

import java.util.function.Function;

public class ResponseDonoCampanhaMapper implements Function<Ong, DonoCampanhaResponse> {

    @Override
    public DonoCampanhaResponse apply(Ong ong) {
        return DonoCampanhaResponse.builder()
                .id(ong.getUsuario().getId())
                .foto(ong.getUsuario().getFoto())
                .nome(ong.getUsuario().getNome())
                .build();
    }
}
