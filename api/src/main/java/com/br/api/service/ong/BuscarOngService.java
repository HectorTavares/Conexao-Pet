package com.br.api.service.ong;

import com.br.api.domain.constants.ExceptionsMessage;
import com.br.api.domain.model.Ong;
import com.br.api.repository.OngRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BuscarOngService {

    private final OngRepository repository;

    public Ong porIdOng(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionsMessage.ONG_NAO_ENCONTRADA));
    }

    public Ong porIdUsuario(Long id) {
        return repository.findByIdUsuario(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionsMessage.ONG_NAO_ENCONTRADA));
    }
}
