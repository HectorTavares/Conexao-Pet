package com.br.api.domain.request;

import com.br.api.domain.response.AnimalModelResponse;
import com.br.api.domain.response.BuscaUsuarioResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BuscarUsuariosEAnimaisResponse {

    private final List<BuscaUsuarioResponse> voluntarios;

    private final List<BuscaUsuarioResponse> ongs;

    private final List<AnimalModelResponse> animais;

}
