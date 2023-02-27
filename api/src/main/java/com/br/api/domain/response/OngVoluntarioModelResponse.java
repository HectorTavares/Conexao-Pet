package com.br.api.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OngVoluntarioModelResponse {

    private OngModelResponse ongModelResponse;

    private VoluntarioModelReponse voluntarioModelReponse;

}
