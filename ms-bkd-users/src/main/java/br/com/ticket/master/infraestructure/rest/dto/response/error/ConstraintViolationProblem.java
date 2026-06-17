package br.com.ticket.master.infraestructure.rest.dto.response.error;

import java.util.List;

public record ConstraintViolationProblem(
        String type,
        String title,
        int status,
        String detail,
        List<Violation> violations
) {
    public record Violation(String field, String message) {}
}