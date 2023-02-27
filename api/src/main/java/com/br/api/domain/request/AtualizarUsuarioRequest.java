package com.br.api.domain.request;

import lombok.Data;

@Data
public class AtualizarUsuarioRequest {

    private String nome;

    private String telefone;

    private String endereco;

}
