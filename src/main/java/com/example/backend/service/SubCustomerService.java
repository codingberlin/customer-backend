package com.example.backend.service;

import com.example.backend.controller.CreateSubCustomerRequest;
import com.example.backend.controller.SubCustomerRecord;
import com.example.backend.persistance.SubCustomer;
import com.example.backend.security.CustomerPrincipal;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubCustomerService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public SubCustomerRecord create(final CreateSubCustomerRequest request, final CustomerPrincipal principal) {
        final var subCustomerEntity = new SubCustomer(
                principal,
                request.taxId(),
                request.firstName(),
                request.lastName(),
                request.comment(),
                request.addressStreet(),
                request.addressHouseNumber(),
                request.addressZipCode(),
                request.addressCountry()
        );

        entityManager.persist(subCustomerEntity);

        return subCustomerEntity.toRecord();
    }

}
