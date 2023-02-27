package com.br.api.service.voluntario;

import com.br.api.domain.constants.ExceptionsMessage;
import com.br.api.domain.model.Voluntario;
import com.br.api.repository.VoluntarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BuscarVoluntarioService {

    private final VoluntarioRepository repository;

    public Voluntario porIdVoluntario(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionsMessage.USUARIO_NAO_ENCONTRADO));
    }

    public Voluntario porIdUsuario(Long id) {
        return repository.findByIdUsuario(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionsMessage.USUARIO_NAO_ENCONTRADO));
    }
}
