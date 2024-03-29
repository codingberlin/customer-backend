package com.example.backend.controller;

import com.example.backend.service.VatIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VatIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VatId {
    String message() default "Must be a valid VatId";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
