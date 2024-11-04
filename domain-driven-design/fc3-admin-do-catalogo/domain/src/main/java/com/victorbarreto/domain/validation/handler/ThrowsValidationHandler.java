package com.victorbarreto.domain.validation.handler;

import com.victorbarreto.domain.exceptions.DomainException;
import com.victorbarreto.domain.validation.Error;
import com.victorbarreto.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final com.victorbarreto.domain.validation.Error anError) {
        throw DomainException.withErrors(List.of(anError));
    }

    @Override
    public ValidationHandler append(final ValidationHandler validationHandler) {
        throw DomainException.withErrors(validationHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.withErrors(List.of(new Error(ex.getMessage())));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }

}
