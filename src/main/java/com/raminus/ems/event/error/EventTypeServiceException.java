package com.raminus.ems.event.error;

import java.util.Arrays;
import java.util.List;

public class EventTypeServiceException extends RuntimeException {

    private final String errorId;
    private final List<String> details;

    public EventTypeServiceException(String errorId, String...details ) {
        super("");
        this.errorId = errorId;
        this.details = Arrays.asList(details);
    }

    public EventTypeServiceException(String errorId, Exception exception, String...details ) {
        super(exception);
        this.errorId = errorId;
        this.details = Arrays.asList(details);
    }

    public String getErrorId() {
        return this.errorId;
    }

    public List<String> getDetails() {
        return this.details;
    }
}
