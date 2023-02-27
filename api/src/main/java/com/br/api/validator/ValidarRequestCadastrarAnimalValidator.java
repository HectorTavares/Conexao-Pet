package com.br.api.validator;

import com.br.api.domain.constants.ExceptionsMessage;
import com.br.api.domain.request.CadastrarAnimalDataRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class ValidarRequestCadastrarAnimalValidator implements Consumer<CadastrarAnimalDataRequest> {

    @Override
    public void accept(CadastrarAnimalDataRequest cadastrarAnimalDataRequest) {

        if (isNull(cadastrarAnimalDataRequest)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.REQUEST_NULA);
        }

        if (isNull(cadastrarAnimalDataRequest.getDataDeNascimento())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.CAMPO_NULO);
        }

        if (isNull(cadastrarAnimalDataRequest.getPorte())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.CAMPO_NULO);
        }

        if (isNull(cadastrarAnimalDataRequest.getEspecie())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.CAMPO_NULO);
        }

        if (isNull(cadastrarAnimalDataRequest.getGenero())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.CAMPO_NULO);
        }

        if (isBlank(cadastrarAnimalDataRequest.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.CAMPO_VAZIO);
        }

        if (isBlank(cadastrarAnimalDataRequest.getCaracteristicas())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.CAMPO_VAZIO);
        }

        if (isBlank(cadastrarAnimalDataRequest.getRaca())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.CAMPO_VAZIO);
        }

        if (isNull(cadastrarAnimalDataRequest.getSituacaoVacinal())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.CAMPO_VAZIO);
        }
    }

}
