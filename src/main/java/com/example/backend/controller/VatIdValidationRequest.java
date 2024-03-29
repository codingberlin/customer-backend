package com.example.backend.controller;

import jakarta.validation.constraints.NotNull;

public record VatIdValidationRequest(@NotNull String vatId) {
}
