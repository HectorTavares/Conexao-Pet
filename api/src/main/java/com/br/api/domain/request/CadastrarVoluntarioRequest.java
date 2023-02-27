package com.br.api.domain.request;

import lombok.Data;

@Data
public class CadastrarVoluntarioRequest {

    private String cpf;

    private CadastrarUsuarioRequest usuario;

}
