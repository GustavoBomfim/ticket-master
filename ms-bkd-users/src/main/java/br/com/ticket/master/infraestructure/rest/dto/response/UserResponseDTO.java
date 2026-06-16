package br.com.ticket.master.infraestructure.rest.dto.response;

import java.util.UUID;

public record UserResponseDTO(
        UUID userId,
        String name,
        String email
) {
}
