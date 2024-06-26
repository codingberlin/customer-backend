package com.example.backend.persistance;

import com.example.backend.controller.SubCustomerRecord;
import com.example.backend.security.CustomerPrincipal;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class SubCustomer {

    @Id
    @UuidGenerator
    private UUID id;
    private UUID customerId;
    private String vatId;
    private String firstName;
    private String lastName;
    private String comment;
    private String addressStreet;
    private String addressHouseNumber;
    private String addressZipCode;
    private String addressCity;
    private String addressCountry;

    public SubCustomer() {

    }

    public SubCustomer(
            final CustomerPrincipal principal,
            final String vatId,
            final String firstName,
            final String lastName,
            final String comment,
            final String addressStreet,
            final String addressHouseNumber,
            final String addressZipCode,
            final String addressCity,
            final String addressCountry) {
        this.customerId = principal.getCustomerId();
        this.vatId = vatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.comment = comment;
        this.addressStreet = addressStreet;
        this.addressHouseNumber = addressHouseNumber;
        this.addressZipCode = addressZipCode;
        this.addressCity = addressCity;
        this.addressCountry = addressCountry;
    }

    public SubCustomerRecord toRecord() {
        return new SubCustomerRecord(
                this.id,
                this.customerId,
                this.vatId,
                this.firstName,
                this.lastName,
                this.comment,
                this.addressStreet,
                this.addressHouseNumber,
                this.addressZipCode,
                this.addressCity,
                this.addressCountry
        );
    }
}
