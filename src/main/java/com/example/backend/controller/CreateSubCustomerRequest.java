package com.example.backend.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateSubCustomerRequest(

        @VatId String vatId,
        @NotNull String firstName,
        @NotNull String lastName,
        @Size(max = 100) String comment,
        String addressStreet,
        String addressHouseNumber,
        String addressZipCode,
        String addressCity,
        String addressCountry
) {
}
