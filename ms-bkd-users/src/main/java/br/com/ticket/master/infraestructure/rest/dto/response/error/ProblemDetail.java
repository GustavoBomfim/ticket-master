package br.com.ticket.master.infraestructure.rest.dto.response.error;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProblemDetail(
        String type,
        String title,
        int status,
        String detail,
        String instance
) {
    public ProblemDetail(String title, int status, String detail) {
        this("about:blank", title, status, detail, null);
    }
}