package com.br.api.validator;

import com.br.api.domain.model.Campanha;
import com.br.api.domain.model.Voluntario;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ValidarEnvioNotificacaoValidator {


    public static Boolean isValid(Campanha campanha, Voluntario voluntario) {

        if (campanha.getInteresses().isEmpty()) {
            return false;
        }

        if (isBlank(voluntario.getUsuario().getTelefone())) {
            return false;
        }

        return true;
    }
}
