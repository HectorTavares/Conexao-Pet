package com.br.api.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuscaUsuarioResponse {

    private Long id;

    private String nome;

    private String endereco;

    private String foto;

    private boolean isVoluntario;


}
