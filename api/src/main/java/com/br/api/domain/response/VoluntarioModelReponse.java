package com.br.api.domain.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VoluntarioModelReponse {

    private Long id;

    private Long idUsuario;

    private String cpf;

    private List<OngModelResponse> ongSeguidas;

    private List<InteresseModelResponse> interesses;

    private String nome;

    private String email;

    private String endereco;

    private String foto;

    private String telefone;

    private List<String> permissions;

}
