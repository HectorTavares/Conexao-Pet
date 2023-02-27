package com.br.api.validator;

import com.br.api.domain.constants.ExceptionsMessage;
import com.br.api.domain.request.CadastrarVoluntarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@RequiredArgsConstructor
public class ValidarRequestCadastroVoluntarioValidator implements Consumer<CadastrarVoluntarioRequest> {

    private final ValidarRequestCadastroUsuarioValidator validator;

    @Override
    public void accept(CadastrarVoluntarioRequest request) {

        validator.accept(request.getUsuario());

        if (isBlank(request.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionsMessage.CAMPO_VAZIO);
        }
    }
}
