package com.br.api.service.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwillioService {

    @Value("${twilio.ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${twilio.AUTH_TOKEN}")
    private String AUTH_TOKEN;

    private static final String prefix = "whatsapp:+55";

    public void enviarMensagem(String numero, String body) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String numeroA = formatToNumber(numero);

        Message message = Message.creator(
                        new PhoneNumber(numeroA),
                        new PhoneNumber("whatsapp:+14155238886"),
                        body)
                .create();

        System.out.println(message);
    }

    private String formatToNumber(String numero) {
        return prefix.concat(numero);
    }

}
