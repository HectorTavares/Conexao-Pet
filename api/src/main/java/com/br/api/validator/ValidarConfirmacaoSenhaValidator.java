package com.br.api.validator;

import com.br.api.domain.request.CadastrarUsuarioRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;

import static com.br.api.domain.constants.ExceptionsMessage.CONFIRMACAO_SENHA_INVALIDA;

@Component
public class ValidarConfirmacaoSenhaValidator implements Consumer<CadastrarUsuarioRequest> {

    @Override
    public void accept(CadastrarUsuarioRequest request) {
        if (!request.getSenha().equals(request.getConfirmacaoSenha())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, CONFIRMACAO_SENHA_INVALIDA);
        }
    }
}
