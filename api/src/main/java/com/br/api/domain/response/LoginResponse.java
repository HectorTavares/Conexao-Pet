package com.br.api.domain.response;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {

    private Long idUsuario;

    private List<PermissionResponse> permissoes;

}
