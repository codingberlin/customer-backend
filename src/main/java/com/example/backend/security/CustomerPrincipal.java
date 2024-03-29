package com.example.backend.security;

import java.security.Principal;
import java.util.UUID;

public class CustomerPrincipal implements Principal {

    @Override
    public String getName() {
        return null;
    }

    public UUID getCustomerId() {
        return UUID.fromString("06fbb35a-eba7-4179-a856-62da9c3b9375");
    }
}
