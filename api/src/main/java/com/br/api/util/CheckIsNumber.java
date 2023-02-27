package com.br.api.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CheckIsNumber {

    public boolean check(String value) {
        return StringUtils.isNumeric(value);
    }
}
