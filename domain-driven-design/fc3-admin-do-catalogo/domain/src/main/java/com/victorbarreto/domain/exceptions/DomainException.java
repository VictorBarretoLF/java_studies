package com.victorbarreto.domain.exceptions;

import java.util.List;

import com.victorbarreto.domain.validation.Error;

public class DomainException extends NoStackTraceException {

    private final List<Error> errors;

    private DomainException(final String aMessage, final List<Error> anErros) {
        super(aMessage);
        this.errors = anErros;
    }

    public static DomainException with(final Error anErrors) {
        return new DomainException(anErrors.message(), List.of(anErrors));
    }

    public static DomainException with(final List<Error> anErrors) {
        return new DomainException("", anErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }

}
