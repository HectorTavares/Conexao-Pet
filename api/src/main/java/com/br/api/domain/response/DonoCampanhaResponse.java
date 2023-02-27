package com.br.api.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DonoCampanhaResponse {

    private Long id;

    private String nome;

    private String foto;

}
