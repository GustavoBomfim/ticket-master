package br.com.ticket.master.infraestructure.rest.dto.response;

import java.util.UUID;

public record AddressResponseDTO(
        UUID id,
        String street,
        String number,
        String city,
        String state,
        String country,
        String zipcode
) {}