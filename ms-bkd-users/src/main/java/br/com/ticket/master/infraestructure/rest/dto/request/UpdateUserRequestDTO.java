package br.com.ticket.master.infraestructure.rest.dto.request;

public record UpdateUserRequestDTO(
        String name,
        String email
) {
}
