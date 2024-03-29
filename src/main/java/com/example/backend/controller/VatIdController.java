package com.example.backend.controller;

import com.example.backend.service.VatIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class VatIdController {

    @Autowired
    private VatIdValidator vatIdValidator;

    @PostMapping("/validate/vat-id")
    public ResponseEntity<ValidationResult> vatId(@Validated @RequestBody VatIdValidationRequest vatId) {
        return ResponseEntity.ok(vatIdValidator.isValid(vatId.vatId()));
    }
}
