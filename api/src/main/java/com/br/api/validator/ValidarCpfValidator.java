package com.br.api.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidarCpfValidator {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}");

    public static boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }
        Matcher matcher = CPF_PATTERN.matcher(cpf);
        if (!matcher.matches()) {
            return false;
        }
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) {
            return false;
        }
        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }
        if (isAllSameDigits(digits)) {
            return false;
        }
        if (!isValidChecksum(digits)) {
            return false;
        }
        return true;
    }

    private static boolean isAllSameDigits(int[] digits) {
        for (int i = 1; i < digits.length; i++) {
            if (digits[i] != digits[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidChecksum(int[] digits) {
        int firstVerificationDigit = calculateVerificationDigit(digits, 9);
        int secondVerificationDigit = calculateVerificationDigit(digits, 10);
        return digits[9] == firstVerificationDigit && digits[10] == secondVerificationDigit;
    }

    private static int calculateVerificationDigit(int[] digits, int position) {
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += digits[i] * (position + 1 - i);
        }
        int remainder = sum % 11;
        if (remainder == 0 || remainder == 1) {
            return 0;
        } else {
            return 11 - remainder;
        }
    }

}
