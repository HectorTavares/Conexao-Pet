package com.br.api.mapper;


import com.br.api.domain.model.Voluntario;
import com.br.api.domain.response.OngModelResponse;
import com.br.api.domain.response.VoluntarioModelReponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ResponseVoluntarioMapper implements Function<Voluntario, VoluntarioModelReponse> {

    private final PermissionMapper permissionMapper = new PermissionMapper();

    private final ResponseInteresseMapper interesseMapper = new ResponseInteresseMapper();

    private final ResponseOngMapper responseOngMapper = new ResponseOngMapper();

    @Override
    public VoluntarioModelReponse apply(Voluntario voluntario) {
        return VoluntarioModelReponse.builder()
                .cpf(voluntario.getCpf())
                .id(voluntario.getId())
                .interesses(interesseMapper.apply(voluntario))
                .idUsuario(voluntario.getUsuario().getId())
                .foto(voluntario.getUsuario().getFoto())
                .email(voluntario.getUsuario().getEmail())
                .telefone(voluntario.getUsuario().getTelefone())
                .endereco(voluntario.getUsuario().getEndereco())
                .nome(voluntario.getUsuario().getNome())
                .ongSeguidas(getOngsSeguidas(voluntario))
                .permissions(permissionMapper.listPermissionsResponse(voluntario.getUsuario().getPermissions()))
                .build();
    }

    private List<OngModelResponse> getOngsSeguidas(Voluntario voluntario) {
        return voluntario.getOngsSeguidas().stream().map(responseOngMapper::apply).collect(Collectors.toList());
    }
}
