package com.project.dvc_barber_service.config.handler.exception;

public class ResourceForbiddenException extends RuntimeException {
    public ResourceForbiddenException(String message) {
        super(message);
    }
}
