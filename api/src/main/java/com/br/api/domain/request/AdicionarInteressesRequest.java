package com.br.api.domain.request;

import lombok.Data;

import java.util.List;

@Data
public class AdicionarInteressesRequest {

    private List<String> interesses;

}
