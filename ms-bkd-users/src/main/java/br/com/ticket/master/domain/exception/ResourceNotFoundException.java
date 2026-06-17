package br.com.ticket.master.domain.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, UUID id) {
        super(String.format("%s com ID '%s' não encontrado.", resourceName, id.toString()));
    }
}