package com.br.api.mapper;

import com.br.api.domain.model.Permission;
import com.br.api.domain.response.PermissionResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PermissionMapper implements Function<Permission, PermissionResponse> {

    @Override
    public PermissionResponse apply(Permission permission) {
        return PermissionResponse.builder()
                .name(permission.getName())
                .build();
    }

    public List<String> listPermissionsResponse(List<Permission> permissoes) {
        return permissoes.stream().map(Permission::getName).collect(Collectors.toList());
    }
}
