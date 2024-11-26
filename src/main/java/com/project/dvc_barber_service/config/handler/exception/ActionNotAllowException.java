package com.project.dvc_barber_service.config.handler.exception;

public class ActionNotAllowException extends RuntimeException {
    public ActionNotAllowException(String message) {
        super(message);
    }
}
