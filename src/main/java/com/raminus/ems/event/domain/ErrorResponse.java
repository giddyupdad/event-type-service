package com.raminus.ems.event.domain;

import com.raminus.ems.event.error.ErrorCatalog;

import java.util.List;

public class ErrorResponse {

    private List<ErrorCatalog> errors;

    public List<ErrorCatalog> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorCatalog> errors) {
        this.errors = errors;
    }
}
