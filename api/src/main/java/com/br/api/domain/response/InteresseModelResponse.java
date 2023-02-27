package com.br.api.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InteresseModelResponse {

    private Long id;

    private String nome;

}
