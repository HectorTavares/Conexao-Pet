package com.br.api.service.security;

import com.br.api.domain.response.LoginResponse;
import com.br.api.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LoginUserService {

    @Autowired
    private AuthenticatedUserService authenticatedUser;

    private final PermissionMapper mapper = new PermissionMapper();

    public LoginResponse login() {
        LoginResponse response = new LoginResponse();
        response.setIdUsuario(authenticatedUser.getId());
        response.setPermissoes(authenticatedUser.get().getPermissions().stream()
                .map(mapper)
                .collect(Collectors.toList()));

        return response;
    }
}

