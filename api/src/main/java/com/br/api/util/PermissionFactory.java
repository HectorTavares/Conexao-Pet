package com.br.api.util;

import com.br.api.domain.enumaration.Permissao;
import com.br.api.domain.model.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionFactory {
    public Permission make(Permissao permissao) {
        return Permission.builder()
                .name(permissao.getPermissao())
                .build();
    }
}
