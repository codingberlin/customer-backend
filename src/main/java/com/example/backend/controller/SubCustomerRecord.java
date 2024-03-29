package com.example.backend.controller;

import java.util.UUID;

public record SubCustomerRecord(
        UUID id,
        UUID customerId,
        String taxId,
        String firstName,
        String lastName,
        String comment,
        String addressStreet,
        String addressHouseNumber,
        String addressZipCode,
        String addressCountry
) {
}
