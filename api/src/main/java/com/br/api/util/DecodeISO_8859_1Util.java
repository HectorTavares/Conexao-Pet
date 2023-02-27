package com.br.api.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class DecodeISO_8859_1Util {


    public String decode(String stringToDocode) {
        byte[] isoBytes = stringToDocode.getBytes(StandardCharsets.ISO_8859_1);

        return new String(isoBytes, StandardCharsets.UTF_8);
    }

}
