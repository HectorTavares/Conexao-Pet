package com.br.api.service.twilio;

import com.br.api.domain.model.Campanha;
import com.br.api.domain.model.Voluntario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.br.api.validator.ValidarEnvioNotificacaoValidator.isValid;

@Service
@RequiredArgsConstructor
public class SendNotificationService {

    private final TwillioService twillioService;

    public void send(Campanha campanha, List<Voluntario> voluntarioParaNotificar) {

        voluntarioParaNotificar.forEach(v -> {
            try {
                if (isValid(campanha, v)) {
                    twillioService.enviarMensagem(v.getUsuario().getTelefone(), makeBody(campanha));
                }
            } catch (Exception e) {
                System.out.println("Erro ao enviar notificação");
            }
        });

    }

    private String makeBody(Campanha campanha) {
        return "A campanha "
                .concat(campanha.getTitulo().trim())
                .concat(" se encaixa nos seus interesses \n")
                .concat("Descrição: ")
                .concat(campanha.getDescricao())
                .concat("\n")
                .concat("Dê: ")
                .concat(campanha.getDono().getUsuario().getNome())
                .concat("\n")
                .concat("Entre em contato por ")
                .concat(campanha.getDono().getUsuario().getTelefone());
    }

}
