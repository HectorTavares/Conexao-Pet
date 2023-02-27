package com.br.api.validator;

import com.br.api.domain.request.CadastrarUsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;

import static com.br.api.domain.constants.ExceptionsMessage.*;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
@RequiredArgsConstructor
public class ValidarRequestCadastroUsuarioValidator implements Consumer<CadastrarUsuarioRequest> {

    private static final Long TAMANHO_MINIMO_SENHA = 6L;

    private final ValidarConfirmacaoSenhaValidator validator;

    @Override
    public void accept(CadastrarUsuarioRequest request) {

        if (isNull(request)) {
            throw new ResponseStatusException(BAD_REQUEST, REQUEST_NULA);
        }

        if (isBlank(request.getNome())) {
            throw new ResponseStatusException(BAD_REQUEST, CAMPO_VAZIO);
        }

        if (isBlank(request.getSenha())) {
            if (request.getSenha().length() < TAMANHO_MINIMO_SENHA) {
                throw new ResponseStatusException(BAD_REQUEST, TAMANHO_INVALIDO_SENHA);
            }
            throw new ResponseStatusException(BAD_REQUEST, CAMPO_VAZIO);
        }

        if (isBlank(request.getEmail())) {
            throw new ResponseStatusException(BAD_REQUEST, CAMPO_VAZIO);
        }

        if (isBlank(request.getTelefone())) {
            throw new ResponseStatusException(BAD_REQUEST, CAMPO_VAZIO);
        }

        if (isBlank(request.getConfirmacaoSenha())) {
            throw new ResponseStatusException(BAD_REQUEST, CAMPO_VAZIO);
        }

        validator.accept(request);
    }
}
