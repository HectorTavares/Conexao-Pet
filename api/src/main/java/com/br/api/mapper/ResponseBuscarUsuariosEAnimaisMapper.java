package com.br.api.mapper;

import com.br.api.domain.request.BuscarUsuariosEAnimaisResponse;
import com.br.api.domain.response.AnimalModelResponse;
import com.br.api.domain.response.BuscaUsuarioResponse;
import org.apache.commons.lang3.function.TriFunction;

import java.util.List;

public class ResponseBuscarUsuariosEAnimaisMapper implements TriFunction<List<BuscaUsuarioResponse>, List<BuscaUsuarioResponse>, List<AnimalModelResponse>, BuscarUsuariosEAnimaisResponse> {

    @Override
    public BuscarUsuariosEAnimaisResponse apply(List<BuscaUsuarioResponse> voluntarios, List<BuscaUsuarioResponse> ongs, List<AnimalModelResponse> animais) {
        return BuscarUsuariosEAnimaisResponse.builder()
                .ongs(ongs)
                .voluntarios(voluntarios)
                .animais(animais)
                .build();
    }
}
