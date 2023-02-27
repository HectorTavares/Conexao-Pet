package com.br.api.mapper;

import com.br.api.domain.model.Campanha;
import com.br.api.domain.response.CampanhaModelResponse;

import java.util.function.Function;

public class ResponseCampanhaMapper implements Function<Campanha, CampanhaModelResponse> {

    private final ResponseFotoMapper responseFotoMapper = new ResponseFotoMapper();

    private final ResponseDonoCampanhaMapper responseDonoCampanhaMapper = new ResponseDonoCampanhaMapper();

    @Override
    public CampanhaModelResponse apply(Campanha campanha) {
        return CampanhaModelResponse.builder()
                .titulo(campanha.getTitulo())
                .descricao(campanha.getDescricao())
                .dataEncerramento(campanha.getDataEncerramento())
                .telefone(campanha.getDono().getUsuario().getTelefone())
                .id(campanha.getId())
                .dataCriacao(campanha.getDataCriacao())
                .fotos(responseFotoMapper.apply(campanha.getFotos()))
                .donoCampanha(responseDonoCampanhaMapper.apply(campanha.getDono()))
                .build();
    }

}
