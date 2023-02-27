package com.br.api.domain.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OngModelResponse {

    private Long id;

    private Long idUsuario;

    private String nome;

    private String email;

    private String foto;

    private String telefone;

    private String endereco;

    private List<String> permissions;

    private List<String> imagens;

    private List<AnimalModelResponse> animais;

    private List<CampanhaModelResponse> campanhas;

}
