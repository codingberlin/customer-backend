package com.example.backend;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class VatIdValidator {

    private static final Pattern[] PATTERNS = {
            Pattern.compile("DE\\d{9}"),
            Pattern.compile("ATU\\d{8}"),
            Pattern.compile("DK\\d{8}"),
            Pattern.compile("NL[A-Z0-9*+]{10}\\d{2}"),
            Pattern.compile("GB([A-Z0-9]{5}|\\d{9}|\\d{12})"),
            Pattern.compile("FR[A-Z0-9]{2}\\d{9}")
    };

    // Because this is a demonstration, I didn't verify the rules with the Bundeszentralamt. e.g. GB has confusing rules at the Wikipedia page.
    // For production code I would verify accordingly
    public ValidationResult isValid(final String vatId) {
        for (final var p : PATTERNS) {
            if (p.matcher(vatId).matches()) {
                return new ValidationResult(true);
            }
        }
        return new ValidationResult(false);
    }
}
