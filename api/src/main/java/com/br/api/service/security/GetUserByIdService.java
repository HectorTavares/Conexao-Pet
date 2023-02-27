package com.br.api.service.security;

import com.br.api.domain.model.Usuario;
import com.br.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class GetUserByIdService {

    @Autowired
    private UsuarioRepository userRepository;


    public Usuario findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuario não encontrado"));
    }
}

