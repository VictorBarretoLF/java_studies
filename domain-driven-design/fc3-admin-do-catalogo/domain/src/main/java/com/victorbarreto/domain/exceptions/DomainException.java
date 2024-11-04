package com.victorbarreto.domain.exceptions;

import java.util.List;

import com.victorbarreto.domain.validation.Error;

public class DomainException extends RuntimeException {

    private final List<Error> errors;

    private DomainException(final List<Error> anErros) {
        super("", null, true, false);
        this.errors = anErros;
    }

    public static DomainException withErrors(final List<Error> anErros) {
        return new DomainException(anErros);
    }

    public List<Error> getErrors() {
        return errors;
    }

}
