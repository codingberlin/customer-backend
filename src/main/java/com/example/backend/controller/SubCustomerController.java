package com.example.backend.controller;

import com.example.backend.security.CustomerPrincipal;
import com.example.backend.service.SubCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubCustomerController {

    @Autowired
    private SubCustomerService subCustomerService;

    @PostMapping("/sub-customers")
    public ResponseEntity<SubCustomerRecord> create(@Validated @RequestBody final CreateSubCustomerRequest request) {
        // the customerPrincipal shall be retrieved from the authentication token via spring security
        final var principal = new CustomerPrincipal();
        return ResponseEntity.ok(subCustomerService.create(request, principal));
    }
}
