package br.com.ticket.master.infraestructure.rest.dto.request;


public record CreateUserRequestDTO(

        String name,
        String email,
        String password
) {
}
