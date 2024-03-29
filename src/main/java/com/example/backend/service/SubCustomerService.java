package com.example.backend.service;

import com.example.backend.controller.CreateSubCustomerRequest;
import com.example.backend.controller.SubCustomerRecord;
import com.example.backend.persistance.SubCustomer;
import com.example.backend.persistance.SubCustomerRepository;
import com.example.backend.security.CustomerPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubCustomerService {

    @Autowired
    private SubCustomerRepository subCustomerRepository;

    @Transactional
    public SubCustomerRecord create(final CreateSubCustomerRequest request, final CustomerPrincipal principal) {
        final var subCustomerEntity = new SubCustomer(
                principal,
                request.vatId(),
                request.firstName(),
                request.lastName(),
                request.comment(),
                request.addressStreet(),
                request.addressHouseNumber(),
                request.addressZipCode(),
                request.addressCity(),
                request.addressCountry()
        );

        return subCustomerRepository.save(subCustomerEntity).toRecord();
    }

    public List<SubCustomerRecord> list(final CustomerPrincipal principal) {
        return subCustomerRepository.findAllByCustomerIdOrderByLastNameAscFirstNameAsc(principal.getCustomerId()).stream()
                .map(SubCustomer::toRecord)
                .toList();
    }
}
