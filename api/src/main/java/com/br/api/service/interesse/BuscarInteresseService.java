package com.br.api.service.interesse;

import com.br.api.domain.model.Interesse;
import com.br.api.repository.InteresseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.br.api.domain.constants.ExceptionsMessage.INTERESSE_NAO_ENCONTRADO;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BuscarInteresseService {

    private final InteresseRepository repository;

    public Interesse porId(final Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, INTERESSE_NAO_ENCONTRADO));
    }

    public Optional<Interesse> porNome(final String nome) {
        return repository.findFirstByNome(nome);
    }

}
