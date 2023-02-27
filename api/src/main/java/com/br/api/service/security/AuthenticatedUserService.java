package com.br.api.service.security;

import com.br.api.domain.model.Usuario;
import com.br.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticatedUserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Long getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        return userSecurity.getId();
    }

    public Usuario get() {
        return usuarioRepository.findById(getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usúario não encontrado"));
    }
}

