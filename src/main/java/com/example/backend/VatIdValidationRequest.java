package com.example.backend;

import jakarta.validation.constraints.NotNull;

public record VatIdValidationRequest(@NotNull String vatId) {
}
