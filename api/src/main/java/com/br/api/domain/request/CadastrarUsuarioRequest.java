package com.br.api.domain.request;


import lombok.Data;

@Data
public class CadastrarUsuarioRequest {

    private String nome;

    private String email;

    private String senha;

    private String endereco;

    private String confirmacaoSenha;

    private String telefone;
}
